package com.example.asystentnauczyciela.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.asystentnauczyciela.data.entities.Course
import com.example.asystentnauczyciela.data.entities.Grade
import com.example.asystentnauczyciela.data.entities.Student

@Database(
    entities = [Student::class, Grade::class, Course::class],
    version = 2
)
abstract class AssistantDatabase: RoomDatabase() {

    abstract val dao: AssistantDao
}