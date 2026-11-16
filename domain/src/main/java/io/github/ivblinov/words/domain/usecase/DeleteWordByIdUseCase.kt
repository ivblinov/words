package io.github.ivblinov.words.domain.usecase

import io.github.ivblinov.words.domain.repository.WordRepository
import javax.inject.Inject

/** Сценарий удаления словарного слова по id. */
class DeleteWordByIdUseCase @Inject constructor(
    private val repository: WordRepository
) {

    /** Удаляет словарное слово по переданному [wordId].
     *
     * @param wordId id удаляемого слова.
     */
    suspend operator fun invoke(wordId: Long): Int = repository.deleteById(wordId)
}