package io.github.ivblinov.words.data.repository_impl

import io.github.ivblinov.words.data.local.dao.WordDao
import io.github.ivblinov.words.data.local.entity.WordEntity
import io.github.ivblinov.words.data.mapper.toData
import io.github.ivblinov.words.data.mapper.toDomain
import io.github.ivblinov.words.data.mapper.toPreview
import io.github.ivblinov.words.domain.model.WordModel
import io.github.ivblinov.words.domain.model.WordModelPreview
import io.github.ivblinov.words.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/** Реализация репозитория словарных слов для работы с базой данных. */
class WordRepositoryImpl @Inject constructor(
    private val wordDao: WordDao,
) : WordRepository {

    /**
     * Реализация сохранения словарного слова.
     *
     * Преобразует данные из [WordModel] (слой домена) в [WordEntity] (слой данных).
     */
    override suspend fun saveWord(wordModel: WordModel): Long {
        return wordDao.upsert(wordModel.toData())
    }

    /**
     * Реализация получения потока словарных слов.
     *
     * Трансформирует данные из [WordEntity] (слой данных) в [WordModelPreview] (слой домена)
     * с помощью операторов [map]. Благодаря реактивности Room, любые изменения в таблице
     * 'word' будут автоматически вызывать повторную эмиссию обновленного списка.
     */
    override fun getWordList(): Flow<List<WordModelPreview>> {
        return wordDao.getWordList().map { entities ->
            entities.map { entity ->
                entity.toPreview()
            }
        }
    }

    /**
     * Реализация получения словарного слова по id.
     *
     * Трансформирует данные из [WordEntity] (слой данных) в [WordModel] (слой домена)
     * с помощью оператора [map].
     *
     * @param wordId id слова в базе данных.
     */
    override fun getWordById(wordId: Long): Flow<WordModel?> {
        return wordDao.getWordById(wordId).map { wordEntity ->
            wordEntity?.toDomain()
        }
    }

    /**
     * Реализация удаления словарного слова по переданному [wordId].
     *
     * @param wordId id слова в базе данных.
     */
    override suspend fun deleteById(wordId: Long): Int {
        return wordDao.deleteById(wordId)
    }
}