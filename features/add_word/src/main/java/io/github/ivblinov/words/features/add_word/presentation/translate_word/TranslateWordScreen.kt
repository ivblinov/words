package io.github.ivblinov.words.features.add_word.presentation.translate_word

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.ivblinov.words.core.resources.R
import io.github.ivblinov.words.core.ui.components.WordFAB
import io.github.ivblinov.words.core.ui.components.WordScaffold
import io.github.ivblinov.words.core.ui.dimens.Spacing

@Composable
fun TranslateWordScreen(
    onBackClick: () -> Unit,
    viewModel: TranslateWordViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is TranslateWordEffect.NavigateBack -> onBackClick()
            }
        }
    }

    val focusManager = LocalFocusManager.current

    WordScaffold(
        title = stringResource(id = R.string.add_word_title),
        onBackClick = onBackClick,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            WordFAB(
                onClick = { viewModel.handleEvent(TranslateWordEvent.OnSaveClick) }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_check_24),
                    contentDescription = stringResource(R.string.save_content_description)
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                OutlinedTextField(
                    value = uiState.fields[TranslateField.WORD] ?: "",
                    onValueChange = {
                        viewModel.handleEvent(
                            TranslateWordEvent.OnFieldChanged(
                                TranslateField.WORD,
                                it
                            )
                        )
                    },
                    modifier = Modifier.fillMaxSize(),
                    label = { Text(stringResource(TranslateField.WORD.labelRes)) },
                    singleLine = true,

                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    )
                )
                Spacer(modifier = Modifier.height(Spacing.spacer_height_between_items))
            }

            item {
                OutlinedTextField(
                    value = uiState.fields[TranslateField.TRANSLATE] ?: "",
                    onValueChange = {
                        viewModel.handleEvent(
                            TranslateWordEvent.OnFieldChanged(
                                TranslateField.TRANSLATE,
                                it
                            )
                        )
                    },
                    modifier = Modifier.fillMaxSize(),
                    label = { Text(stringResource(TranslateField.TRANSLATE.labelRes)) },
                    singleLine = true,

                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    )
                )
            }
        }
    }
}