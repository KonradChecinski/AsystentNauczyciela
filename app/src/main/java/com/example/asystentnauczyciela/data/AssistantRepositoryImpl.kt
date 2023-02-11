package com.example.asystentnauczyciela.data

import com.example.asystentnauczyciela.data.entities.Course
import com.example.asystentnauczyciela.data.entities.Grade
import com.example.asystentnauczyciela.data.entities.Student
import com.example.asystentnauczyciela.data.relations.StudentWithGrades
import kotlinx.coroutines.flow.Flow

class AssistantRepositoryImpl(
    private val dao: AssistantDao
): AssistantRepository {
    //Student
    override fun getStudents(): Flow<List<Student>> {
        return dao.getStudents()
    }
    override suspend fun addStudent(student: Student) {
        dao.addStudent(student)
    }

    override suspend fun deleteStudent(student: Student) {
        dao.deleteStudent(student)
    }

    override suspend fun getStudentById(studentId: Int): Student? {
        return dao.getStudentById(studentId)
    }

    override suspend fun getStudentWithGrades(studentId: Int): List<StudentWithGrades> {
        return dao.getStudentWithGrades(studentId)
    }


    //Grade
    override suspend fun addGrade(grade: Grade) {
        dao.addGrade(grade)
    }

    //Course

    override fun getCourses(): Flow<List<Course>> {
        return dao.getCourses()
    }

    override suspend fun getCourseById(courseId: Int): Course? {
        return dao.getCourseById(courseId)
    }

    override suspend fun addCourse(course: Course) {
        return dao.addCourse(course)
    }

    override suspend fun deleteCourse(course: Course) {
        dao.deleteCourse(course)
    }



//    override suspend fun getStudentWithGradesInCourse(studentId: Int, classId: Int): List<StudentWithGrades>{
//        return dao.getStudentWithGradesInCourse(studentId, classId)
//    }

}