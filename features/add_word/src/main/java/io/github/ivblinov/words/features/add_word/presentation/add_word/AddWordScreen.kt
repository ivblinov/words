package io.github.ivblinov.words.features.add_word.presentation.add_word

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AddWordScreen(
    viewModel: AddWordViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    AddWordContent(uiState)
}

@Composable
fun AddWordContent(
    uiState: AddWordUiState
) {
    when (uiState) {
        is AddWordUiState.Error -> ErrorContent(uiState.message)
        is AddWordUiState.Success -> SuccessContent(uiState.word)
        AddWordUiState.Loading -> LoadingContent()
    }
}

@Composable
private fun ErrorContent(message: String) {
    ContentPattern(message)
}

@Composable
private fun SuccessContent(word: String) {
    ContentPattern(word)
}

@Composable
private fun LoadingContent() {
    CircularProgressIndicator()
}

@Composable
private fun ContentPattern(str: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = str,
            fontSize = 28.sp
        )
    }
}

@Preview(showBackground = false)
@Composable
private fun AddWordScreen() {

}