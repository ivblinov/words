package io.github.ivblinov.words.domain.usecase

import io.github.ivblinov.words.domain.model.WordModel
import io.github.ivblinov.words.domain.model.result.SaveWordResult
import io.github.ivblinov.words.domain.model.utils.trimWord
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
     * Сохраняет словарное слово после предварительной обработки и валидации.
     *
     * Удаляет пробелы в начале и конце английского слова и перевода.
     * Проверяет, что поле с английским словом не пустое.
     *
     * @param wordModel Доменная модель словарного слова для сохранения.
     * @return ID сохранённой записи.
     * @throws IllegalArgumentException если английское слово пустое.
     */
    suspend operator fun invoke(wordModel: WordModel): SaveWordResult {
        val word = wordModel.trimWord()

        require(word.engName.isNotEmpty()) {
            "English word field cannot be empty"
        }

        val id = repository.saveWord(word)

        return if (id == -1L) {
            SaveWordResult.Duplicate
        } else {
            SaveWordResult.Success(id)
        }
    }
}