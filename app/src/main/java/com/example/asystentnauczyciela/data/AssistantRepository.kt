package com.example.asystentnauczyciela.data

import com.example.asystentnauczyciela.data.entities.Grade
import com.example.asystentnauczyciela.data.entities.Student
import com.example.asystentnauczyciela.data.relations.StudentWithGrades
import kotlinx.coroutines.flow.Flow

interface AssistantRepository {
    suspend fun addStudent(student: Student)

    suspend fun addGrade(grade: Grade)

    suspend fun deleteStudent(student: Student)

    suspend fun getStudentById(studentId: Int): Student?

    fun getStudents(): Flow<List<Student>>

    suspend fun getStudentWithGrades(studentId: Int): List<StudentWithGrades>

//    suspend fun getStudentWithGradesInCourse(studentId: Int, classId: Int): List<StudentWithGrades>

}