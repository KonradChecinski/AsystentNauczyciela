package com.example.asystentnauczyciela.data.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.asystentnauczyciela.data.entities.Course
import com.example.asystentnauczyciela.data.entities.Student

data class StudentWithCourses(
    @Embedded val student: Student,
    @Relation(
        parentColumn = "studentId",
        entityColumn = "courseId",
        associateBy = Junction(StudentCourseCrossRef::class)
    )
    val courses: List<Course>
)