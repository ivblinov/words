package io.github.ivblinov.words.data.mapper

import io.github.ivblinov.words.data.local.entity.WordEntity
import io.github.ivblinov.words.domain.model.WordModelPreview

/**
 * Преобразует сущность базы данных [WordEntity] в облегченную
 * доменную модель [WordModelPreview].
 */
fun WordEntity.toPreview() = WordModelPreview(
    id = id,
    engName = engName
)