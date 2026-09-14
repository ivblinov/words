package io.github.ivblinov.words.data.repository_impl

import io.github.ivblinov.words.data.local.dao.WordDao
import io.github.ivblinov.words.data.local.db.AppDatabase
import io.github.ivblinov.words.data.local.entity.WordEntity
import io.github.ivblinov.words.data.mapper.toPreview
import io.github.ivblinov.words.domain.model.WordModelPreview
import io.github.ivblinov.words.domain.repository.WordRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/** Реализация репозитория словарных слов для работы с базой данных. */
class WordRepositoryImpl @Inject constructor(
    private val db: AppDatabase,
    private val wordDao: WordDao,
) : WordRepository {

//    override suspend fun saveWord(wordModel: WordModel): Result<Unit> {
//        Log.d(TAG, "saveWord: ")
//    }

    /**
     * Реализация получения потока словарных слов.
     *
     * Трансформирует данные из [WordEntity] (слой данных) в [WordModelPreview] (слой домена)
     * с помощью операторов [map]. Благодаря реактивности Room, любые изменения в таблице
     * 'word' будут автоматически вызывать повторную эмиссию обновленного списка.
     */
    override fun getWordList(): List<WordModelPreview> {
        return wordDao.getWordList().map { entities ->
            entities.toPreview()
        }
    }
}