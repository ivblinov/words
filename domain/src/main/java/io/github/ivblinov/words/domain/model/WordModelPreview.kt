package io.github.ivblinov.words.domain.model

/**
 * Краткая информация о словарном слове для отображения в списке слов словаря.
 *
 * @property id Уникальный идентификатор слова
 * @property engName Название слова на английском
 */
data class WordModelPreview(
    val id: Long,
    val engName: String
)