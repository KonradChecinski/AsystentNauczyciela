package com.example.asystentnauczyciela.data

import com.example.asystentnauczyciela.data.entities.Course
import com.example.asystentnauczyciela.data.entities.Grade
import com.example.asystentnauczyciela.data.entities.Student
import com.example.asystentnauczyciela.data.relations.StudentWithGrades
import kotlinx.coroutines.flow.Flow

interface AssistantRepository {
    //Student
    fun getStudents(): Flow<List<Student>>

    suspend fun addStudent(student: Student)

    suspend fun deleteStudent(student: Student)

    suspend fun getStudentById(studentId: Int): Student?

    suspend fun getStudentWithGrades(studentId: Int): List<StudentWithGrades>

    //Grade
    suspend fun addGrade(grade: Grade)


    //Course

    fun getCourses(): Flow<List<Course>>

    fun getCourseById(courseId: Int): Course?

    suspend fun addCourse(course: Course)

    suspend fun deleteCourse(course: Course)


//    suspend fun getStudentWithGradesInCourse(studentId: Int, classId: Int): List<StudentWithGrades>

}