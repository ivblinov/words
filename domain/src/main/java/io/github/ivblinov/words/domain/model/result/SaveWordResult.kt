package io.github.ivblinov.words.domain.model.result

sealed interface SaveWordResult {

    data class Success(val id: Long) : SaveWordResult

    data object Duplicate : SaveWordResult
}