package io.github.ivblinov.words.features.add_word.presentation.word_detail

import io.github.ivblinov.words.domain.model.WordModel

sealed interface WordDetailUiState {

    /** Процесс первичной загрузки данных из БД. */
    data object Loading : WordDetailUiState

    /**
     * Данные успешно получены и готовы к отображению.
     * @property wordDetail Вся детальная информация о словарном слове [WordModel].
     */
    data class Success(val wordDetail: WordModel) : WordDetailUiState

    /**
     * Состояние при возникновении ошибки в цепочке данных (БД или маппинг).
     * @property error Исключение, описывающее причину сбоя.
     */
    data class Error(val error: Throwable) : WordDetailUiState
}