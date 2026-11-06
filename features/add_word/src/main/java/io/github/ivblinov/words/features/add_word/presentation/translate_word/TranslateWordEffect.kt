package io.github.ivblinov.words.features.add_word.presentation.translate_word

/**
 * Разовые события (эффекты), которые не являются частью состояния.
 */
sealed interface TranslateWordEffect {

    /** Успешное сохранение — команда на закрытие экрана. */
    data object NavigateBack : TranslateWordEffect
}