package com.mobiai.app.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mobiai.app.App
import com.mobiai.base.basecode.service.db.TestModelDatabase

@Database(entities = [Note::class], version = 2)
abstract class AppDataBase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object{
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabaseClient(): AppDataBase {
            if (INSTANCE != null) return INSTANCE!!

            synchronized(this){
                INSTANCE = Room
                    .databaseBuilder(
                        App.getInstance().applicationContext ,
                        AppDataBase::class.java,
                        "AppDataBase").
                    fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!
            }
        }
    }
}