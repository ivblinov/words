package io.github.ivblinov.words.data.mapper

import io.github.ivblinov.words.data.local.entity.WordEntity
import io.github.ivblinov.words.domain.model.WordModel

/**
 * Преобразует доменную модель [WordModel] в сущность базы данных [WordEntity].
 */
fun WordModel.toData() = WordEntity(
    engName = engName,
    ruName = ruName
)

/**
 * Преобразует сущность базы данных [WordEntity] в доменную модель [WordModel].
 */
fun WordEntity.toDomain() = WordModel(
    id = id,
    engName = engName,
    ruName = ruName
)