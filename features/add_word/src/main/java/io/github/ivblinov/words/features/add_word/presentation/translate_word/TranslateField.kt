package io.github.ivblinov.words.features.add_word.presentation.translate_word

import androidx.annotation.StringRes
import io.github.ivblinov.words.core.resources.R

enum class TranslateField(@get:StringRes val labelRes: Int) {

    WORD(R.string.field_word_eng),
    TRANSLATE(R.string.field_word_ru)
}