package com.example.asystentnauczyciela.data

import androidx.room.*
import com.example.asystentnauczyciela.data.entities.Course
import com.example.asystentnauczyciela.data.entities.Grade
import com.example.asystentnauczyciela.data.entities.Student
import com.example.asystentnauczyciela.data.relations.StudentWithGrades
import kotlinx.coroutines.flow.Flow

@Dao
interface AssistantDao {

    //region Student
    @Query("SELECT * FROM Student")
    fun getStudents(): Flow<List<Student>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("SELECT * FROM Student WHERE studentId = :studentId")
    suspend fun getStudentById(studentId: Int): Student?

    @Transaction
    @Query("SELECT * FROM Student WHERE studentId = :studentId")
    suspend fun getStudentWithGrades(studentId: Int): List<StudentWithGrades>
    //endregion


    //region Grade
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGrade(grade: Grade)

    //endregion


    //region Course
    @Query("SELECT * FROM Course")
    fun getCourses(): Flow<List<Course>>

    @Query("SELECT * FROM Course WHERE courseId = :courseId")
    suspend fun getCourseById(courseId: Int): Course?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCourse(course: Course)

    @Delete
    suspend fun deleteCourse(course: Course)

    //endregion


//    @Transaction
//    @Query("SELECT * FROM Student JOIN Grade ON (Student.studentId=Grade.studentId AND classId = :classId) WHERE studentId = :studentId")
//    suspend fun getStudentWithGradesInCourse(studentId: Int, classId: Int): List<StudentWithGrades>
}