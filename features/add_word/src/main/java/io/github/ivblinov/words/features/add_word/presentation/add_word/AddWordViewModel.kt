package io.github.ivblinov.words.features.add_word.presentation.add_word

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.ivblinov.words.domain.model.WordModelPreview
import io.github.ivblinov.words.domain.usecase.GetWordListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddWordViewModel @Inject constructor(
    private val getWordListUseCase: GetWordListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AddWordUiState>(AddWordUiState.Loading)
    val uiState: StateFlow<AddWordUiState> = _uiState.asStateFlow()

//    private var allWords: List<String> = listOf()
    private var allWords: List<WordModelPreview> = listOf()

    init {
        loadInitialWords()
    }

    fun handleEvent(event: AddWordEvent) {
        when (event) {
            is AddWordEvent.OnSearchQueryChanged -> filterWords(event.query)
        }
    }

    private fun loadInitialWords() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                allWords = getWordListUseCase()
//                allWords = listOf("Apple", "Banana", "Orange", "Cherry", "Plum")
                _uiState.value = AddWordUiState.Success(
                    wordList = allWords,
                    searchQuery = ""
                )
            } catch (e: Throwable) {
                _uiState.value = AddWordUiState.Error(e)
            }
        }
    }

    private fun filterWords(query: String) {
        val currentState = _uiState.value
        if (currentState is AddWordUiState.Success) {
            val filteredList = if (query.isBlank()) {
                allWords
            } else {
                allWords.filter { it.engName.contains(query, ignoreCase = true) }
//                allWords.filter { it.contains(query, ignoreCase = true) }
            }

            _uiState.value = currentState.copy(
                wordList = filteredList,
                searchQuery = query
            )
        }
    }
}