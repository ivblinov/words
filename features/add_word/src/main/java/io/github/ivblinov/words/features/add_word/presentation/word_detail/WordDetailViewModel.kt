package io.github.ivblinov.words.features.add_word.presentation.word_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.ivblinov.words.domain.usecase.DeleteWordByIdUseCase
import io.github.ivblinov.words.domain.usecase.GetWordByIdUseCase
import io.github.ivblinov.words.features.add_word.navigation.WordDetailRoute
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getWordByIdUseCase: GetWordByIdUseCase,
    private val deleteWordByIdUseCase: DeleteWordByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<WordDetailUiState>(WordDetailUiState.Loading)
    val uiState: StateFlow<WordDetailUiState> = _uiState.asStateFlow()

    private val _effect = Channel<WordDetailEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    private val wordId = savedStateHandle
        .toRoute<WordDetailRoute>()
        .wordId

    init {
        loadWord()
    }

    fun handleEvent(event: WordDetailEvent) {
        when (event) {
            WordDetailEvent.DeleteWord -> deleteWord()
        }
    }

    /**  Инициализация экрана. Подписываемся на словарное слово из базы данных.  */
    private fun loadWord() {
        viewModelScope.launch {
            getWordByIdUseCase(wordId)
                .catch { e ->
                    _uiState.value = WordDetailUiState.Error(e)
                }
                .collect { word ->
                    _uiState.value = if (word != null) {
                        WordDetailUiState.Success(word)
                    } else {
                        WordDetailUiState.Error(
                            NoSuchElementException("Word with id $wordId not found")
                        )
                    }
                }
        }
    }

    /**  Удаление словарного слова по [wordId]  */
    private fun deleteWord() {
        viewModelScope.launch {
            deleteWordByIdUseCase(wordId)
            _effect.send(WordDetailEffect.NavigateBack)
        }
    }
}