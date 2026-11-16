package io.github.ivblinov.words.features.add_word.presentation.word_detail

/**
 * Разовые события (эффекты), которые не являются частью состояния.
 */
interface WordDetailEffect {

    /** Успешное сохранение — команда на закрытие экрана. */
    data object NavigateBack : WordDetailEffect
}