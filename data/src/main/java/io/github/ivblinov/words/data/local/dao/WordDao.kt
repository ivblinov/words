package io.github.ivblinov.words.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import io.github.ivblinov.words.data.local.entity.WordEntity
import kotlinx.coroutines.flow.Flow

/**
 * Объект доступа к данным (DAO) для управления сущностями основной информации о словарном слове [WordEntity].
 *
 * Обеспечивает стандартные операции сохранения и удаления через наследование [BaseDao].
 */
@Dao
interface WordDao : BaseDao<WordEntity> {

    /** Получает список всех словарных слов (только ID и имя), отсортированных по алфавиту. */
    @Query("SELECT * FROM word ORDER BY engName ASC")
    fun getWordList(): Flow<List<WordEntity>>

    /**
     * Получает словарное слово по его ID.
     */
    @Query("SELECT * FROM word WHERE id = :wordId")
    fun getWordById(wordId: Long): Flow<WordEntity?>

    /**
     * Удаляет словарное слово по его ID.
     */
    @Query("DELETE FROM word WHERE id = :wordId")
    suspend fun deleteById(wordId: Long): Int
}