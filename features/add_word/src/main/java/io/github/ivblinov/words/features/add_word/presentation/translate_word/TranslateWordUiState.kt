package io.github.ivblinov.words.features.add_word.presentation.translate_word

import androidx.compose.runtime.Immutable

/**
 * Состояние пользовательского интерфейса экрана добавления перевода слова.
 *
 * @property fields Значения слов для перевода: ключ — тип поля, значение — текст пользователя.
 * @property fieldErrors Ошибки валидации текстовых полей: ключ — поле с ошибкой,
 * значение — ID ресурса текста ошибки.
 * @property isLoading Флаг процесса сохранения словарного слова в базу данных.
 */

@Immutable
data class TranslateWordUiState(
    val fields: Map<TranslateField, String> = emptyMap(),
    val fieldErrors: Map<TranslateField, Int> = emptyMap(),
    val isLoading: Boolean = false
)