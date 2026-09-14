package io.github.ivblinov.words.data.local.dao

import androidx.room.Delete
import androidx.room.Upsert

/**
 * Базовый интерфейс для всех DAO (Data Access Objects).
 *
 * Определяет общие операции вставки, обновления и удаления для сущностей [T].
 *
 * @param T Тип сущности базы данных.
 */
interface BaseDao<T> {

    /**
     * Сохраняет сущность в базе данных.
     *
     * Если запись с таким первичным ключом отсутствует, будет произведена вставка (Insert).
     * Если запись уже существует, она будет обновлена (Update) новыми значениями.
     *
     * @param entity Объект для сохранения.
     * @return ID сохраненной записи (первичный ключ).
     */
    @Upsert
    suspend fun upsert(entity: T): Long

    /**
     * Сохраняет список сущностей в базе данных одной операцией.
     *
     * Аналогично [upsert], обновляет существующие записи или вставляет новые.
     *
     * @param entities Список объектов для сохранения.
     */
    @Upsert
    suspend fun upsertAll(entities: List<T>)

    /**
     * Удаляет указанную сущность из базы данных.
     *
     * Поиск записи для удаления производится по первичному ключу объекта [entity].
     *
     * @param entity Объект, который необходимо удалить.
     */
    @Delete
    suspend fun delete(entity: T)
}