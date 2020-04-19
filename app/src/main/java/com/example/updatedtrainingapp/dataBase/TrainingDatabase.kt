package com.example.updatedtrainingapp.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.updatedtrainingapp.dataBase.dao.ExerciseDao
import com.example.updatedtrainingapp.dataBase.dao.ExerciseInfoDao
import com.example.updatedtrainingapp.dataBase.dao.TrainingDao
import com.example.updatedtrainingapp.dataBase.objects.ExerciseInfoObject
import com.example.updatedtrainingapp.dataBase.objects.ExerciseObject
import com.example.updatedtrainingapp.dataBase.objects.TrainingObject

@Database(
    entities = [ExerciseObject::class, TrainingObject::class, ExerciseInfoObject::class],
    version = 1
)
abstract class TrainingDatabase : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao
    abstract fun trainingDao(): TrainingDao
    abstract fun exerciseInfoDao(): ExerciseInfoDao

    companion object {
        @Volatile
        private var INSTANCE: TrainingDatabase? = null

        fun getDatabase(context: Context): TrainingDatabase? {
            if (INSTANCE == null) {
                synchronized(TrainingDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            TrainingDatabase::class.java,
                            DatabaseContract.DATABASE_NAME
                        ).build()
                    }
                }
            }
            return INSTANCE
        }
    }
}