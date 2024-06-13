package com.mobven.fitai.data.repository

import com.mobven.fitai.common.ResponseState
import com.mobven.fitai.data.model.entity.WorkoutEntity
import com.mobven.fitai.data.model.response.GeneratePlanResponse
import com.mobven.fitai.data.model.response.Program
import com.mobven.fitai.domain.repository.WorkoutRepository
import com.mobven.fitai.domain.source.LocalDataSource
import com.mobven.fitai.domain.source.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WorkoutRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : WorkoutRepository {

    override fun generateWorkoutPlan(authToken: String): Flow<ResponseState<GeneratePlanResponse>> {
        return flow {
            emit(ResponseState.Loading)
            val response = remoteDataSource.generateWorkoutPlan(authToken)
            response.fitness_antrenman.forEach {
                localDataSource.insertWorkout(
                    WorkoutEntity(
                        day = it.day,
                        workoutList = it.program
                    )
                )
            }
            emit(ResponseState.Success(response))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun getWorkoutList(): Flow<ResponseState<List<WorkoutEntity>>> {
        return flow {
            emit(ResponseState.Loading)
            val response = localDataSource.getWorkoutList()
            emit(ResponseState.Success(response))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun getWorkoutByDay(workoutDay: String): Flow<ResponseState<WorkoutEntity>> {
        return flow {
            emit(ResponseState.Loading)
            val response = localDataSource.getWorkoutByDay(workoutDay)
            emit(ResponseState.Success(response))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override fun updateWorkoutList(workoutDay: String, workoutList: List<Program>) =
        localDataSource.updateWorkoutList(workoutDay, workoutList)

    override fun insertWorkout(workoutEntity: WorkoutEntity) =
        localDataSource.insertWorkout(workoutEntity)

}