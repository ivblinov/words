package io.github.ivblinov.words.core.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordScaffold(
    title: String,
    snackbarHost: @Composable () -> Unit,
    onBackClick: (() -> Unit)? = null,
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            WordTopAppBar(
                title = title,
                onBackClick = onBackClick
            )
        },
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton
    ) { padding ->
        content(padding)
    }
}