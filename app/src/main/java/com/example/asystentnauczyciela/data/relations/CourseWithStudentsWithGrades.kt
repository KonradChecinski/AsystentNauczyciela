package com.example.asystentnauczyciela.data.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.asystentnauczyciela.data.entities.Course
import com.example.asystentnauczyciela.data.entities.Student

data class CourseWithStudentsWithGrades(
    @Embedded val course: Course,
    @Relation(
        entity = Student::class,
        parentColumn = "courseId",
        entityColumn = "studentId",
        associateBy = Junction(StudentCourseCrossRef::class)
    )
    val students: List<StudentWithGrades>
)
