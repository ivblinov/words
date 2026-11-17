package io.github.ivblinov.words.features.add_word.presentation.translate_word

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.intl.LocaleList
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.ivblinov.words.core.resources.R
import io.github.ivblinov.words.core.ui.components.WordFAB
import io.github.ivblinov.words.core.ui.components.WordScaffold
import io.github.ivblinov.words.core.ui.dimens.Spacing

private val enLocale = Locale("en")
private val ruLocale = Locale("ru")

@Composable
fun TranslateWordScreen(
    onBackClick: () -> Unit,
    viewModel: TranslateWordViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is TranslateWordEffect.NavigateBack -> onBackClick()
            }
        }
    }

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(Spacing.screen_padding),
            verticalArrangement = Arrangement.spacedBy(Spacing.spacer_height_between_items)
        ) {
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
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                label = { Text(stringResource(TranslateField.WORD.labelRes)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    hintLocales = LocaleList(enLocale)
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                isError = uiState.fieldErrors[TranslateField.WORD] != null,
                supportingText = {
                    uiState.fieldErrors[TranslateField.WORD]?.let { errorRes ->
                        Text(stringResource(errorRes))
                    }
                }
            )

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
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(TranslateField.TRANSLATE.labelRes)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                    hintLocales = LocaleList(ruLocale)
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ),
                isError = uiState.fieldErrors[TranslateField.TRANSLATE] != null,
                supportingText = {
                    uiState.fieldErrors[TranslateField.TRANSLATE]?.let { errorRes ->
                        Text(stringResource(errorRes))
                    }
                }
            )
        }
    }
}