package com.example.asystentnauczyciela.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Course(
    @PrimaryKey val classId: Int? = null,
    val className: String,
    val weekDay: Int,
    val timeBlock: String
)
