package com.example.myplants.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.myplants.feature_main.data.datasource.PlantDatabase
import com.example.myplants.feature_main.data.repository.PlantLocalRepositoryImpl
import com.example.myplants.feature_main.data.repository.TaskLocalRepositoryImpl
import com.example.myplants.feature_main.domain.repository.PlantLocalRepository
import com.example.myplants.feature_main.domain.repository.TaskLocalRepository
import com.example.myplants.feature_main.domain.usecase.plant.DeletePlant
import com.example.myplants.feature_main.domain.usecase.plant.GetAllPlants
import com.example.myplants.feature_main.domain.usecase.plant.GetPlant
import com.example.myplants.feature_main.domain.usecase.plant.PlantUseCase
import com.example.myplants.feature_main.domain.usecase.plant.SavePlant
import com.example.myplants.feature_main.domain.usecase.task.GetNextTask
import com.example.myplants.feature_main.domain.usecase.task.GetTask
import com.example.myplants.feature_main.domain.usecase.task.SaveTask
import com.example.myplants.feature_main.domain.usecase.task.TaskUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDatabase(app: Application): PlantDatabase {
        return Room.databaseBuilder(
            context = app,
            klass = PlantDatabase::class.java,
            name = PlantDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun providePlantRepository(database: PlantDatabase): PlantLocalRepository {
        return PlantLocalRepositoryImpl(database.plantDao)
    }

    @Provides
    fun provideTaskRepository(database: PlantDatabase): TaskLocalRepository {
        return TaskLocalRepositoryImpl(database.taskDao)
    }

    @Provides
    fun providePlantUseCase(plantLocalRepository: PlantLocalRepository): PlantUseCase {
        return PlantUseCase(
            getAll = GetAllPlants(plantLocalRepository),
            get = GetPlant(plantLocalRepository),
            save = SavePlant(plantLocalRepository),
            delete = DeletePlant(plantLocalRepository)
        )
    }

    @Provides
    fun provideTaskUseCase(taskLocalRepository: TaskLocalRepository): TaskUseCase {
        return TaskUseCase(
            get = GetTask(taskLocalRepository),
            getNext = GetNextTask(taskLocalRepository),
            save = SaveTask(taskLocalRepository)
        )
    }

    @Provides
    fun provideGlide(@ActivityContext context: Context): RequestManager {
        return Glide.with(context)
    }
}