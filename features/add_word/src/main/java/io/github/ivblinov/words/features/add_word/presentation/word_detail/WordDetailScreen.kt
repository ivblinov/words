package io.github.ivblinov.words.features.add_word.presentation.word_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import io.github.ivblinov.words.core.resources.R
import io.github.ivblinov.words.core.ui.components.WordFAB
import io.github.ivblinov.words.core.ui.components.WordScaffold
import io.github.ivblinov.words.core.ui.dimens.Spacing
import io.github.ivblinov.words.domain.model.WordModel
import io.github.ivblinov.words.features.add_word.presentation.translate_word.TranslateWordEffect

@Composable
fun WordDetailScreen(
    navController: NavController,
    viewModel: WordDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is WordDetailEffect.NavigateBack -> navController.popBackStack()
            }
        }
    }

    WordDetailContent(
        uiState = uiState,
        snackbarHostState = snackbarHostState,
        onBackClick = { navController.popBackStack() },
        onDeleteClick = { viewModel.handleEvent(WordDetailEvent.DeleteWord) }
        )
}

@Composable
fun WordDetailContent(
    uiState: WordDetailUiState,
    snackbarHostState: SnackbarHostState,
    onBackClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    when (uiState) {
        WordDetailUiState.Loading -> LoadingContent()

        is WordDetailUiState.Success -> SuccessContent(
            wordDetail = uiState.wordDetail,
            snackbarHostState = snackbarHostState,
            onBackClick = onBackClick,
            onDeleteClick = onDeleteClick
        )

        is WordDetailUiState.Error -> ErrorContent()
    }
}

@Composable
private fun SuccessContent(
    wordDetail: WordModel,
    snackbarHostState: SnackbarHostState,
    onBackClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    WordScaffold(
        title = stringResource(id = R.string.word_detail_title),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        onBackClick = onBackClick,
        floatingActionButton = {
            WordFAB(
                onClick = onDeleteClick,
                containerColor = MaterialTheme.colorScheme.error
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_delete_24px),
                    contentDescription = stringResource(R.string.delete_content_description),
                )
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(Spacing.screen_padding),
            verticalArrangement = Arrangement.spacedBy(Spacing.spacer_height_between_items)
        ) {
            OutlinedTextField(
                value = wordDetail.engName,
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.field_word_eng))
                },
                readOnly = true,
                singleLine = true
            )

            OutlinedTextField(
                value = wordDetail.ruName,
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.field_word_ru))
                },
                readOnly = true,
                singleLine = true
            )
        }
    }
}

@Composable
private fun LoadingContent() {
    Text(text = "Загружается страница деталей")
}

@Composable
private fun ErrorContent() {

}