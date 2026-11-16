package io.github.ivblinov.words.domain.usecase

import io.github.ivblinov.words.domain.model.WordModel
import io.github.ivblinov.words.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/** Сценарий получения словарного слова по id. */
class GetWordByIdUseCase @Inject constructor(
    private val repository: WordRepository
) {

    /** Возвращает словарное слово [WordModel] по переданному id [wordId]. */
    operator fun invoke(wordId: Long): Flow<WordModel?> = repository.getWordById(wordId)
}