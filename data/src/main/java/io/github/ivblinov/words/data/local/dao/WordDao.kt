package io.github.ivblinov.words.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import io.github.ivblinov.words.data.local.entity.WordEntity

/**
 * Объект доступа к данным (DAO) для управления сущностями основной информации о словарном слове [WordEntity].
 *
 * Обеспечивает стандартные операции сохранения и удаления через наследование [BaseDao].
 */
@Dao
interface WordDao : BaseDao<WordEntity> {

    /** Получает список всех словарных слов (только ID и имя), отсортированных по алфавиту. */
    @Query("SELECT * FROM word ORDER BY engName ASC")
    fun getWordList(): List<WordEntity>
}