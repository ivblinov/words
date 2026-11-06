package io.github.ivblinov.words.features.add_word.presentation.translate_word

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.ivblinov.words.domain.model.WordModel
import io.github.ivblinov.words.domain.usecase.SaveWordUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TranslateWordViewModel @Inject constructor(
    private val saveWordUseCase: SaveWordUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        TranslateWordUiState(
            fields = TranslateField.entries.associateWith { "" }
        )
    )
    val uiState = _uiState.asStateFlow()

    private val _effect = Channel<TranslateWordEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    /**
     * Основная точка входа для всех событий с экрана.
     * Передает обработку конкретным частным методам в зависимости от типа [event].
     */
    fun handleEvent(event: TranslateWordEvent) {
        when (event) {
            is TranslateWordEvent.OnFieldChanged -> updateField(event.field, event.value)
            TranslateWordEvent.OnSaveClick -> saveWord()
        }
    }

    /**
     * Обновляет значение конкретного поля [field] в состоянии UI.
     * Использует атомарное обновление [update] для гарантии потокобезопасности.
     */
    private fun updateField(field: TranslateField, value: String) {
        val sanitizedValue = if (field == TranslateField.WORD) value else value.trim()
        _uiState.update { currentState ->
            currentState.copy(fields = currentState.fields + (field to sanitizedValue))
        }
    }

    /**
     * Метод для сохранения словарного слова в базу данных.
     */
    private fun saveWord() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val wordModel = WordModel(
                id = 0,
                engName = uiState.value.fields[TranslateField.WORD].orEmpty(),
                ruName = uiState.value.fields[TranslateField.TRANSLATE].orEmpty()
            )

            saveWordUseCase(wordModel)
            _uiState.update { it.copy(isLoading = false) }
            _effect.send(TranslateWordEffect.NavigateBack)
        }
    }
}