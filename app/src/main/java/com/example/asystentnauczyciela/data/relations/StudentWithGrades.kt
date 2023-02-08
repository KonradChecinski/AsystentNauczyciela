package com.example.asystentnauczyciela.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.asystentnauczyciela.data.entities.Grade
import com.example.asystentnauczyciela.data.entities.Student

data class StudentWithGrades(
    @Embedded val student: Student,
    @Relation(
        parentColumn = "studentId",
        entityColumn = "studentId"
    )
    val grades: List<Grade>
)
