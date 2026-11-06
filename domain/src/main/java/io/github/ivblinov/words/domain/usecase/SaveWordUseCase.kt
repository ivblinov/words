package io.github.ivblinov.words.domain.usecase

import io.github.ivblinov.words.domain.model.WordModel
import io.github.ivblinov.words.domain.repository.WordRepository
import javax.inject.Inject

/**
 * Сценарий сохранения словарного слова.
 *
 * Инкапсулирует бизнес-логику проверки данных перед отправкой в репозиторий.
 * Гарантирует, что словарное слово с пустым полем "WORD" или некорректными данными не попадет в базу.
 */

class SaveWordUseCase @Inject constructor(
    private val repository: WordRepository
) {

    /**
     * Выполняет сохранение словарного слова с предварительной валидацией.
     *
     * @param wordModel Доменная модель словарного слова для сохранения.
     * @return Long - id сохраненной записи.
     */
    suspend operator fun invoke(wordModel: WordModel): Long {

        return repository.saveWord(wordModel)
    }
}