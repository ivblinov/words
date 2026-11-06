package io.github.ivblinov.words.domain.usecase

import io.github.ivblinov.words.domain.model.WordModelPreview
import io.github.ivblinov.words.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/** Сценарий получения списка словарных слов. */
class GetWordListUseCase @Inject constructor(
    private val repository: WordRepository
) {

    /** Предоставляет список словарных слов [WordModelPreview]. */
    operator fun invoke(): Flow<List<WordModelPreview>> = repository.getWordList()
}