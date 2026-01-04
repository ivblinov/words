package io.github.ivblinov.words.features.add_word.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun AddWordScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "add",
            fontSize = 28.sp
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun AddWordScreenPreview() {
//    WordsTheme {
//        AddWordScreen("Android")
//    }
//}