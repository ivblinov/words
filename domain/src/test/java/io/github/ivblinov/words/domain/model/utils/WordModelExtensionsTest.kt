package io.github.ivblinov.words.domain.model.utils

import io.github.ivblinov.words.domain.model.WordModel
import org.junit.Assert.assertEquals
import org.junit.Test

class WordModelExtensionsTest {

    @Test
    fun trimWord_removesWhitespaceFromBothEnds() {
        // Arrange
        val word = WordModel(
            id = 1L,
            engName = "  apple  ",
            ruName = "  яблоко  "
        )

        // Act
        val result = word.trimWord()

        // Assert
        assertEquals("apple", result.engName)
        assertEquals("яблоко", result.ruName)
    }
}