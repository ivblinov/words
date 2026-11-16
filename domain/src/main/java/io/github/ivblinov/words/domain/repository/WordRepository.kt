package io.github.ivblinov.words.domain.repository

import io.github.ivblinov.words.domain.model.WordModel
import io.github.ivblinov.words.domain.model.WordModelPreview
import kotlinx.coroutines.flow.Flow

/**
 * Интерфейс репозитория для работы с данными словарных слов.
 *
 * Определяет контракт для сохранения, получения и удаления информации о словарных словах,
 * скрывая детали реализации (БД, API) от доменного слоя.
 */
interface WordRepository {

    /**
     * Сохраняет словарное слово со всеми свойствами.
     * Использует операцию Upsert (вставка или обновление).
     *
     * @param wordModel Доменная модель словарного слова.
     * @return Long - id сохраненной записи.
     */
    suspend fun saveWord(wordModel: WordModel): Long

    /**
     * Предоставляет список кратких данных о словарных словах для отображения.
     *
     * @return список словарных слов только на английском [WordModelPreview]
     * Сортировка по алфавиту (А-Я).
     */
    fun getWordList(): Flow<List<WordModelPreview>>

    /**
     * Предоставляет словарное слово из базы данных по переданному [wordId].
     *
     * @param wordId id слова в базе данных.
     */
    fun getWordById(wordId: Long): Flow<WordModel?>

    /**
     * Удаляет словарное слово из базы данных по переданному [wordId].
     *
     * @param wordId id слова в базе данных.
     */
    suspend fun deleteById(wordId: Long): Int
}