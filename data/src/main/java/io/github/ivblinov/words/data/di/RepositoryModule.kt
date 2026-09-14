package io.github.ivblinov.words.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.ivblinov.words.data.repository_impl.WordRepositoryImpl
import io.github.ivblinov.words.domain.repository.WordRepository
import javax.inject.Singleton

/**
 * Модуль внедрения зависимостей для репозиториев данных.
 *
 * Связывает интерфейсы из доменного слоя с их конкретными реализациями
 * в слое данных для использования через Hilt.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * Предоставляет реализацию [WordRepository] для работы с базой данных.
     */
    @Binds
    @Singleton
    abstract fun bindWordRepository(
        wordRepositoryImpl: WordRepositoryImpl
    ): WordRepository
}