package io.github.ivblinov.words.features.add_word.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import io.github.ivblinov.words.features.add_word.presentation.add_word.AddWordScreen
import io.github.ivblinov.words.features.add_word.presentation.translate_word.TranslateWordScreen
import io.github.ivblinov.words.features.add_word.presentation.word_detail.WordDetailScreen

const val wordGraphRoute = "word_graph_route"
const val addWordRoute = "add_word_route"
const val translateWordRoute = "translate_word_route"

fun NavGraphBuilder.wordGraph(
    navController: NavController,
) {
    navigation(
        route = wordGraphRoute,
        startDestination = addWordRoute,
    ) {
        addWordScreen(navController)
        translateWordScreen(navController)
        wordDetailScreen(navController)
    }
}

fun NavGraphBuilder.addWordScreen(navController: NavController) {
    composable(route = addWordRoute) {
        AddWordScreen(navController)
    }
}

fun NavGraphBuilder.translateWordScreen(navController: NavController) {
    composable(route = translateWordRoute) {
        TranslateWordScreen(
            onBackClick = {
                navController.popBackStack()
            }
        )
    }
}

fun NavGraphBuilder.wordDetailScreen(navController: NavController) {
    composable<WordDetailRoute> {
        WordDetailScreen(navController)
    }
}