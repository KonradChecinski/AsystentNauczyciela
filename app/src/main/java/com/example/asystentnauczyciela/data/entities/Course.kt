package com.example.asystentnauczyciela.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Course(
    @PrimaryKey val courseId: Int? = null,
    val courseName: String,
    val weekDay: Int,
    val timeBlock: String
)
