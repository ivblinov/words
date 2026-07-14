package io.github.ivblinov.words.features.add_word.presentation.add_word

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddWordViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<AddWordUiState>(AddWordUiState.Loading)
    val uiState: StateFlow<AddWordUiState> = _uiState.asStateFlow()
}