package io.github.ivblinov.words.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Главная сущность словарного слова в базе данных [WordEntity].
 *
 * Является родительской таблицей для всех остальных таблиц для словарных слов с дополнительной или служебной информацией.
 * Связь с дочерними таблицами осуществляется по [id] через внешние ключи.
 *
 * @property id Уникальный идентификатор словарного слова (автоинкремент).
 * @property engName Название словарного слова на английском языке (например, "Apple, Orange").
 * @property ruName Название словарного слова на русском языке (например, "Яблоко, Апельсин").
 */
@Entity(
    tableName = "word",
    indices = [
        Index(
            value = ["engName", "ruName"],
            unique = true
        )
    ]
)
data class WordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val engName: String,
    val ruName: String
)
