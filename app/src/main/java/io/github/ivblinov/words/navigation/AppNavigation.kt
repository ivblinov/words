package io.github.ivblinov.words.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.github.ivblinov.words.features.add_word.navigation.addWordRoute
import io.github.ivblinov.words.features.add_word.navigation.wordGraph
import io.github.ivblinov.words.features.add_word.navigation.wordGraphRoute

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = wordGraphRoute
    ) {
        wordGraph(navController)
    }
}