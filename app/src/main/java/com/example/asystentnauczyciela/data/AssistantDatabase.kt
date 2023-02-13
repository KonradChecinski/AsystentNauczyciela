package com.example.asystentnauczyciela.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.asystentnauczyciela.data.entities.Course
import com.example.asystentnauczyciela.data.entities.Grade
import com.example.asystentnauczyciela.data.entities.Student
import com.example.asystentnauczyciela.data.relations.CourseWithStudents
import com.example.asystentnauczyciela.data.relations.StudentCourseCrossRef

@Database(
    entities = [Student::class, Grade::class, Course::class, StudentCourseCrossRef::class],
    version = 6
)
abstract class AssistantDatabase: RoomDatabase() {

    abstract val dao: AssistantDao
}