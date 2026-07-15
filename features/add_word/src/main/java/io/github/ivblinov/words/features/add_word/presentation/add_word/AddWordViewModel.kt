package io.github.ivblinov.words.features.add_word.presentation.add_word

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddWordViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<AddWordUiState>(AddWordUiState.Loading)
    val uiState: StateFlow<AddWordUiState> = _uiState.asStateFlow()

    private var allWords: List<String> = emptyList()

    init {
        loadInitialWords()
    }

    fun handleEvent(event: AddWordEvent) {
        when (event) {
            is AddWordEvent.OnSearchQueryChanged -> filterWords(event.query)
        }
    }

    private fun loadInitialWords() {
        viewModelScope.launch {
            try {
                allWords = listOf("Apple", "Banana", "Orange", "Cherry", "Plum")
                _uiState.value = AddWordUiState.Success(
                    words = allWords,
                    searchQuery = ""
                )
            } catch (e: Exception) {
                _uiState.value = AddWordUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private fun filterWords(query: String) {
        val currentState = _uiState.value
        if (currentState is AddWordUiState.Success) {
            val filteredList = if (query.isBlank()) {
                allWords
            } else {
                allWords.filter { it.contains(query, ignoreCase = true) }
            }

            _uiState.value = currentState.copy(
                words = filteredList,
                searchQuery = query
            )
        }
    }
}