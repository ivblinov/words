package io.github.ivblinov.words.features.add_word.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.ivblinov.words.features.add_word.presentation.add_word.AddWordScreen

const val addWordRoute = "add_word_route"

fun NavGraphBuilder.addWordScreen() {
    composable(route = addWordRoute) {
        AddWordScreen()
    }
}