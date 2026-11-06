package io.github.ivblinov.words.features.add_word.presentation.translate_word

/**
 * События пользовательского интерфейса на экране добавления словарного слова.
 */
sealed interface TranslateWordEvent {
    /**
     * Вызывается при изменении значения в любом текстовом поле ввода.
     * @param field Идентификатор изменяемого словарного слова.
     * @param value "Сырые" пользовательские данные, требующие валидации во ViewModel.
     */
    data class OnFieldChanged(val field: TranslateField, val value: String) : TranslateWordEvent

    /** Попытка сохранения словарного слова в базу данных. */
    data object OnSaveClick : TranslateWordEvent
}