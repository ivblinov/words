package io.github.ivblinov.words.features.add_word.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.ivblinov.words.features.add_word.presentation.AddWordScreen

const val addWordRoot = "add_word_root"

fun NavGraphBuilder.addWordScreen() {
    composable(route = addWordRoot) {
        AddWordScreen()
    }
}