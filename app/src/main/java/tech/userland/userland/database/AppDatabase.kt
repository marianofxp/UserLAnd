package tech.userland.userland.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import tech.userland.userland.database.dao.*
import tech.userland.userland.database.entity.*

@Database(entities = arrayOf(SessionEntity::class, FilesystemEntity::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun sessionDao(): SessionDao
    abstract fun filesystemDao(): FilesystemDao

    companion object {


        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "Data.db")
                        .build()
    }
}