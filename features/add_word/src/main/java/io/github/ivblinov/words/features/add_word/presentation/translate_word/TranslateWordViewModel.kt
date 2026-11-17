package io.github.ivblinov.words.features.add_word.presentation.translate_word

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.ivblinov.words.core.resources.R
import io.github.ivblinov.words.domain.model.WordModel
import io.github.ivblinov.words.domain.model.result.SaveWordResult
import io.github.ivblinov.words.domain.usecase.SaveWordUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MyLog"
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
        _uiState.update { currentState ->
            currentState.copy(
                fields = currentState.fields + (field to value),
                fieldErrors = currentState.fieldErrors - field
            )
        }
    }

    /**
     * Метод для сохранения словарного слова в базу данных.
     *
     * Обрабатывает ошибку сохранения пустого поля для английского слова.
     * Обрабатывает ошибку сохранения дубликата пары "слово-перевод".
     */
    private fun saveWord() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            try {
                val wordModel = WordModel(
                    id = 0,
                    engName = uiState.value.fields[TranslateField.WORD].orEmpty(),
                    ruName = uiState.value.fields[TranslateField.TRANSLATE].orEmpty()
                )

                when (saveWordUseCase(wordModel)) {
                    is SaveWordResult.Success -> {
                        _effect.send(TranslateWordEffect.NavigateBack)
                    }

                    SaveWordResult.Duplicate -> {
                        _uiState.update {
                            it.copy(
                                fieldErrors = it.fieldErrors +
                                        (TranslateField.TRANSLATE to R.string.error_duplicate_translation)
                            )
                        }
                    }
                }
            } catch (_: IllegalArgumentException) {
                _uiState.update {
                    it.copy(fieldErrors = it.fieldErrors +
                            (TranslateField.WORD to R.string.error_empty_word))
                }
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }
}