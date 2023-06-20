package com.example.myplants.di

import android.app.Application
import androidx.room.Room
import com.example.myplants.feature_main.data.datasource.PlantDatabase
import com.example.myplants.feature_main.data.repository.PlantLocalRepositoryImpl
import com.example.myplants.feature_main.data.repository.TaskLocalRepositoryImpl
import com.example.myplants.feature_main.domain.repository.PlantLocalRepository
import com.example.myplants.feature_main.domain.repository.TaskLocalRepository
import com.example.myplants.feature_main.domain.usecase.plant.DeletePlant
import com.example.myplants.feature_main.domain.usecase.plant.GetPlant
import com.example.myplants.feature_main.domain.usecase.plant.PlantUseCases
import com.example.myplants.feature_main.domain.usecase.plant.SavePlant
import com.example.myplants.feature_main.domain.usecase.task.DeleteTask
import com.example.myplants.feature_main.domain.usecase.task.GetAll
import com.example.myplants.feature_main.domain.usecase.task.GetTask
import com.example.myplants.feature_main.domain.usecase.task.SaveTask
import com.example.myplants.feature_main.domain.usecase.task.TaskUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun providePlantUseCases(repository: PlantLocalRepository): PlantUseCases {
        return PlantUseCases(
            getPlant = GetPlant(repository),
            savePlant = SavePlant(repository),
            deletePlant = DeletePlant(repository)
        )
    }

    @Provides
    fun provideTaskRepository(database: PlantDatabase): TaskLocalRepository {
        return TaskLocalRepositoryImpl(database.taskDao)
    }

    @Provides
    fun provideTaskUseCases(repository: TaskLocalRepository): TaskUseCases {
        return TaskUseCases(
            getAll = GetAll(repository),
            getTask = GetTask(repository),
            saveTask = SaveTask(repository),
            deleteTask = DeleteTask(repository)
        )
    }
}