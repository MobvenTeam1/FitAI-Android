package com.mobven.fitai.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mobven.fitai.data.local.dao.UserDao
import com.mobven.fitai.data.local.dao.WorkoutDao
import com.mobven.fitai.data.model.entity.UserEntity
import com.mobven.fitai.data.model.entity.WorkoutEntity
import com.mobven.fitai.util.Converters

@Database(entities = [UserEntity::class, WorkoutEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class FitAIDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun workoutDao(): WorkoutDao
}