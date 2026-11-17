package io.github.ivblinov.words.domain.model.utils

import io.github.ivblinov.words.domain.model.WordModel

/**
 * Метод-расширение, который недопускает сохранение в базу данных
 * слов с пробелами до или после слова
 */
fun WordModel.trimWord() = copy(
    engName = engName.trim(),
    ruName = ruName.trim()
)