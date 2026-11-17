package io.github.ivblinov.words.data.local.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {

    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            CREATE UNIQUE INDEX IF NOT EXISTS
            `index_word_engName_ruName`
            ON `word` (`engName`, `ruName`)
            """.trimIndent()
        )
    }
}