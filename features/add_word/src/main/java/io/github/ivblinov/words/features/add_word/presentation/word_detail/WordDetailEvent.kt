package io.github.ivblinov.words.features.add_word.presentation.word_detail

sealed class WordDetailEvent {

    object DeleteWord : WordDetailEvent()
}