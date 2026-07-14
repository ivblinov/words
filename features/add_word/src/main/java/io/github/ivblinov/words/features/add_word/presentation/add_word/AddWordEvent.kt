package io.github.ivblinov.words.features.add_word.presentation.add_word

sealed class AddWordEvent {
    data object LoadAddWord : AddWordEvent()

    /** Экран открылся */
    data object ScreenOpened : AddWordEvent()

    /** Пользователь вводит слово */
    data class WordChanged(val value: String) : AddWordEvent()

    /** Пользователь вводит перевод */
    data class TranslationChanged(val value: String) : AddWordEvent()

    /** Нажата кнопка "Добавить" */
    data object SaveClicked : AddWordEvent()

    /** Очистить сообщение об ошибке */
    data object ErrorShown : AddWordEvent()

    /** Пользователь закрыл экран (опционально) */
    data object ScreenClosed : AddWordEvent()
}