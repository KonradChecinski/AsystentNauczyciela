package com.example.asystentnauczyciela.di

import android.app.Application
import androidx.room.Room
import com.example.asystentnauczyciela.data.AssistantDatabase
import com.example.asystentnauczyciela.data.AssistantRepository
import com.example.asystentnauczyciela.data.AssistantRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(app: Application): AssistantDatabase {
        return Room.databaseBuilder(
            app,
            AssistantDatabase::class.java,
            "todo_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: AssistantDatabase): AssistantRepository {
        return AssistantRepositoryImpl(db.dao)
    }
}