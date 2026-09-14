package io.github.ivblinov.words.domain.model

/**
 * Словарное слово.
 *
 * Базовая доменная модель, объединяющая все свойства словарного слова.
 *
 * @property id Уникальный идентификатор слова
 * @property engName Название слова на английском
 * @property ruName Перевод слова на русский
 */
data class WordModel(
    val id: Long,
    val engName: String,
    val ruName: String
)