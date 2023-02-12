package com.example.asystentnauczyciela.data.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.asystentnauczyciela.data.entities.Course
import com.example.asystentnauczyciela.data.entities.Student

data class CourseWithStudents(
    @Embedded val course: Course,
    @Relation(
        parentColumn = "courseId",
        entityColumn = "studentId",
        associateBy = Junction(StudentCourseCrossRef::class)
    )
    val students: List<Student>
)
