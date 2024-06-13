package com.mobven.fitai.data.source

import com.mobven.fitai.data.local.dao.UserDao
import com.mobven.fitai.data.local.dao.WorkoutDao
import com.mobven.fitai.data.model.entity.UserEntity
import com.mobven.fitai.data.model.entity.WorkoutEntity
import com.mobven.fitai.data.model.response.Program
import com.mobven.fitai.domain.source.LocalDataSource
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val userDao: UserDao,
    private val workoutDao: WorkoutDao
) : LocalDataSource {

    // User
    override fun insertUser(userEntity: UserEntity) {
        userDao.insertUser(userEntity)
    }

    override fun updateUserKey(userKey: String) {
        userDao.updateUserKey(userKey)
    }

    override fun insertUserKey(userKey: String) {
        userDao.insertUserKey(userKey)
    }

    override suspend fun getUserKey(): String {
        return userDao.getUserKey()
    }

    override suspend fun getUser(): UserEntity {
        return userDao.getUser()
    }

    // Workout
    override fun getWorkoutList(): List<WorkoutEntity> {
        return workoutDao.getWorkoutList()
    }

    override fun getWorkoutByDay(workoutDay: String): WorkoutEntity {
        return workoutDao.getWorkoutByDay(workoutDay)
    }

    override fun updateWorkoutList(workoutDay: String, workoutList: List<Program>) {
        workoutDao.updateWorkoutList(workoutDay, workoutList)
    }

    override fun insertWorkout(workoutEntityList: WorkoutEntity) {
        workoutDao.insertWorkout(workoutEntityList)
    }
}