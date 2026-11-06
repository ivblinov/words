package io.github.ivblinov.words.features.add_word.presentation.add_word

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.ivblinov.words.domain.model.WordModelPreview
import io.github.ivblinov.words.domain.usecase.GetWordListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddWordViewModel @Inject constructor(
    private val getWordListUseCase: GetWordListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AddWordUiState>(AddWordUiState.Loading)
    val uiState: StateFlow<AddWordUiState> = _uiState.asStateFlow()

    private var allWords: List<WordModelPreview> = emptyList()

    init {
        loadInitialWords()
    }

    fun handleEvent(event: AddWordEvent) {
        when (event) {
            is AddWordEvent.OnSearchQueryChanged -> filterWords(event.query)
        }
    }

    /**
     * Инициализация экрана. Подписываемся на список слов из базы данных.
     */
    private fun loadInitialWords() {
        viewModelScope.launch {
            getWordListUseCase()
                .catch { e ->
                    _uiState.value = AddWordUiState.Error(e)
                }
                .collect { words ->
                    allWords = words

                    val query = (_uiState.value as? AddWordUiState.Success)?.searchQuery.orEmpty()

                    _uiState.value = AddWordUiState.Success(
                        wordList = if (query.isBlank()) {
                            allWords
                        } else {
                            allWords.filter {
                                it.engName.contains(query, ignoreCase = true)
                            }
                        },
                        searchQuery = query
                    )
                }
        }
    }

    /**
     * Метод для фильтрации списка слов.
     *
     * @param query Запрос пользователя для фильтрации.
     */
    private fun filterWords(query: String) {
        val currentState = _uiState.value

        if (currentState is AddWordUiState.Success) {
            val filteredList = if (query.isBlank()) {
                allWords
            } else {
                allWords.filter {
                    it.engName.contains(query, ignoreCase = true)
                }
            }

            _uiState.value = currentState.copy(
                wordList = filteredList,
                searchQuery = query
            )
        }
    }
}