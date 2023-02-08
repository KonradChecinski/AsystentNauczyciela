package com.example.asystentnauczyciela.data

import com.example.asystentnauczyciela.data.entities.Grade
import com.example.asystentnauczyciela.data.entities.Student
import com.example.asystentnauczyciela.data.relations.StudentWithGrades
import kotlinx.coroutines.flow.Flow

class AssistantRepositoryImpl(
    private val dao: AssistantDao
): AssistantRepository {
    override suspend fun addStudent(student: Student) {
        dao.addStudent(student)
    }

    override suspend fun addGrade(grade: Grade) {
        dao.addGrade(grade)
    }

    override suspend fun deleteStudent(student: Student) {
        dao.deleteStudent(student)
    }

    override suspend fun getStudentById(studentId: Int): Student? {
        return dao.getStudentById(studentId)
    }

    override fun getStudents(): Flow<List<Student>> {
        return dao.getStudents()
    }

    override suspend fun getStudentWithGrades(studentId: Int): List<StudentWithGrades> {
        return dao.getStudentWithGrades(studentId)
    }

//    override suspend fun getStudentWithGradesInCourse(studentId: Int, classId: Int): List<StudentWithGrades>{
//        return dao.getStudentWithGradesInCourse(studentId, classId)
//    }

}