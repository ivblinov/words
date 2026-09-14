package io.github.ivblinov.words.features.add_word.presentation.add_word

import io.github.ivblinov.words.domain.model.WordModelPreview

sealed interface AddWordUiState {

    /** Процесс первичной загрузки данных из БД. */
    data object Loading : AddWordUiState

    /** Состояние, когда в базе данных нет сохраненных слов. */
//    data object Empty : AddWordUiState

    /**
     * Данные успешно получены и готовы к отображению.
     * @property wordList Список кратких моделей словарных слов [WordModelPreview].
     * @property searchQuery Поисковый запрос.
     */
    data class Success(
        val wordList: List<WordModelPreview>,
//        val wordList: List<String>,
        val searchQuery: String
    ) : AddWordUiState

    /**
     * Состояние при возникновении ошибки в цепочке данных (БД или маппинг).
     * @property error Исключение, описывающее причину сбоя.
     */
    data class Error(val error: Throwable) : AddWordUiState
}