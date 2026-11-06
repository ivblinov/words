package io.github.ivblinov.words.data.mapper

import io.github.ivblinov.words.data.local.entity.WordEntity
import io.github.ivblinov.words.domain.model.WordModel

fun WordModel.toData() = WordEntity(
    engName = engName,
    ruName = ruName
)