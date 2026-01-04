package io.github.ivblinov.words

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import io.github.ivblinov.words.features.add_word.presentation.AddWordScreen
import io.github.ivblinov.words.ui.theme.WordsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WordsTheme {
                Surface {
                    AddWordScreen()
                }
            }
        }
    }
}