package com.example.myplants.di

import android.app.Application
import androidx.room.Room
import com.example.myplants.feature_main.data.datasource.PlantDatabase
import com.example.myplants.feature_main.data.repository.PlantRepositoryImpl
import com.example.myplants.feature_main.data.repository.TaskRepositoryImpl
import com.example.myplants.feature_main.domain.repository.PlantRepository
import com.example.myplants.feature_main.domain.repository.TaskRepository
import com.example.myplants.feature_main.domain.usecase.CompleteTask
import com.example.myplants.feature_main.domain.usecase.DeletePlant
import com.example.myplants.feature_main.domain.usecase.GetAll
import com.example.myplants.feature_main.domain.usecase.GetPlant
import com.example.myplants.feature_main.domain.usecase.GetTask
import com.example.myplants.feature_main.domain.usecase.PlantUseCase
import com.example.myplants.feature_main.domain.usecase.SavePlant
import com.example.myplants.feature_notification.data.repository.NotificationRepositoryImpl
import com.example.myplants.feature_notification.domain.repository.NotificationRepository
import com.example.myplants.feature_notification.domain.usecase.GetAllNotifications
import com.example.myplants.feature_notification.domain.usecase.NotificationUseCase
import com.example.myplants.feature_notification.domain.usecase.SaveNotification
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
    fun providePlantRepository(database: PlantDatabase): PlantRepository {
        return PlantRepositoryImpl(database.plantDao)
    }

    @Provides
    fun provideTaskRepository(database: PlantDatabase): TaskRepository {
        return TaskRepositoryImpl(database.taskDao)
    }

    @Provides
    fun provideNotificationRepository(database: PlantDatabase): NotificationRepository {
        return NotificationRepositoryImpl(database.notificationDao)
    }

    @Provides
    fun providePlantUseCase(
        taskRepository: TaskRepository,
        plantRepository: PlantRepository
    ): PlantUseCase {
        return PlantUseCase(
            getAll = GetAll(plantRepository),
            getPlant = GetPlant(plantRepository),
            getTask = GetTask(taskRepository),
            savePlant = SavePlant(taskRepository, plantRepository),
            deletePlant = DeletePlant(plantRepository),
            completeTask = CompleteTask(taskRepository, plantRepository)
        )
    }

    @Provides
    fun provideNotificationUseCase(
        notificationRepository: NotificationRepository
    ): NotificationUseCase {
        return NotificationUseCase(
            getAll = GetAllNotifications(notificationRepository),
            save = SaveNotification(notificationRepository)
        )
    }
}