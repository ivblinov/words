package io.github.ivblinov.words.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.ivblinov.words.data.local.dao.WordDao
import io.github.ivblinov.words.data.local.entity.WordEntity

/** Основная база данных приложения [AppDatabase]. */
@Database(
    entities = [WordEntity::class],
    version = 1,
    exportSchema = false // Изменить на true перед релизом. Настроить папку для exportSchema в build.gradle.kts
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {

        /** Имя файла базы данных в хранилище приложения. */
        const val DATABASE_NAME = "words_db"
    }
}