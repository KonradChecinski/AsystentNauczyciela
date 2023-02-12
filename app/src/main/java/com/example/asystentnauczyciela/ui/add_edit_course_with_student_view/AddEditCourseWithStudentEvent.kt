package com.example.asystentnauczyciela.ui.add_edit_course_with_student_view

import com.example.asystentnauczyciela.data.entities.Course
import com.example.asystentnauczyciela.data.entities.Student
import com.example.asystentnauczyciela.data.relations.StudentWithCourses

sealed class AddEditCourseWithStudentEvent {
    data class OnStudentClick(val studentWithCourses: StudentWithCourses): AddEditCourseWithStudentEvent()
    data class OnSearchBarChange(val text: String): AddEditCourseWithStudentEvent()
}
