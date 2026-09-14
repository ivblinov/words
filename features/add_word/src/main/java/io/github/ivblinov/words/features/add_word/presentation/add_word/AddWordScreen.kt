package io.github.ivblinov.words.features.add_word.presentation.add_word

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.ivblinov.words.core.resources.R
import io.github.ivblinov.words.domain.model.WordModelPreview

@Composable
fun AddWordScreen(
    viewModel: AddWordViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AddWordContent(
        uiState = uiState,
        onSearchQueryChange = { query ->
            viewModel.handleEvent(AddWordEvent.OnSearchQueryChanged(query))
        }
    )
}

@Composable
fun AddWordContent(
    uiState: AddWordUiState,
    onSearchQueryChange: (String) -> Unit
) {
    when (uiState) {
        is AddWordUiState.Error -> ErrorContent(uiState.error.message ?: "Ошибка")
        is AddWordUiState.Success -> SuccessContent(
            words = uiState.wordList,
            searchQuery = uiState.searchQuery,
            onQueryChange = onSearchQueryChange
        )
        AddWordUiState.Loading -> LoadingContent()
    }
}

@Composable
private fun ErrorContent(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun SuccessContent(
//    words: List<String>,
    words: List<WordModelPreview>,
    searchQuery: String,
    onQueryChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onQueryChange,
            label = { Text("Поиск слова") },
            leadingIcon = { Icon(
                painter = painterResource(id = R.drawable.ic_search_24px),
                contentDescription = "Поиск") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(words) { word ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
//                        text = word,
                        text = word.engName,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun LoadingContent() {
    CircularProgressIndicator()
}

@Preview(showBackground = false)
@Composable
private fun AddWordScreen() {

}