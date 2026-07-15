package io.github.ivblinov.words.features.add_word.presentation.add_word

sealed interface AddWordUiState {
    data object Loading : AddWordUiState
    data class Success(val words: List<String>, val searchQuery: String) : AddWordUiState
    data class Error(val message: String) : AddWordUiState
}