package io.github.ivblinov.words.core.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.ivblinov.words.data.local.dao.WordDao
import io.github.ivblinov.words.data.local.db.AppDatabase
import javax.inject.Singleton


/**
 * Модуль внедрения зависимостей для базы данных.
 *
 * Предоставляет единственный экземпляр [AppDatabase] и все доступные DAO
 * для использования в репозиториях слоя данных.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Создает и настраивает экземпляр Room базы данных.
     * Использует [AppDatabase.DATABASE_NAME] для имени файла БД.
     */
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            name = AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideWordDao(db: AppDatabase): WordDao = db.wordDao()
}