package com.example.asystentnauczyciela.data.relations

import androidx.room.Entity

@Entity(primaryKeys = ["studentId", "courseId"])
data class StudentCourseCrossRef(
    val studentId: Int,
    val courseId: Int
)