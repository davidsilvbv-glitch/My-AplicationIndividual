package com.example.myapplicationindividual.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [StarWarsFavoriteEntity::class, StarWarsCacheEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun starWarsFavoriteDao(): StarWarsFavoriteDao
    abstract fun starWarsCacheDao(): StarWarsCacheDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "CREATE TABLE IF NOT EXISTS `starwars_cache` (" +
                        "`id` TEXT NOT NULL, " +
                        "`name` TEXT NOT NULL, " +
                        "`height` TEXT NOT NULL, " +
                        "`mass` TEXT NOT NULL, " +
                        "`gender` TEXT NOT NULL, " +
                        "`birthYear` TEXT NOT NULL, " +
                        "`hairColor` TEXT NOT NULL, " +
                        "`eyeColor` TEXT NOT NULL, " +
                        "`skinColor` TEXT NOT NULL, " +
                        "`imageUrl` TEXT NOT NULL, " +
                        "PRIMARY KEY(`id`))"
                )
            }
        }

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "starwars_app.db"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                    .also { instance = it }
            }
        }
    }
}
