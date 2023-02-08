package com.example.asystentnauczyciela.data

import androidx.room.*
import com.example.asystentnauczyciela.data.entities.Grade
import com.example.asystentnauczyciela.data.entities.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface AssistantDao {


    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addStudent(student: Student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGrade(grade: Grade)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("SELECT * FROM Student WHERE studentId = :studentId")
    suspend fun getStudentById(studentId: Int): Student?

    @Query("SELECT * FROM Student")
    fun getStudents(): Flow<List<Student>>

//    @Transaction
//    @Query("SELECT * FROM Grade WHERE studentId = :studentId")
//    suspend fun getStudentWithGrades(studentId: Int): List<StudentWithGrades>
}