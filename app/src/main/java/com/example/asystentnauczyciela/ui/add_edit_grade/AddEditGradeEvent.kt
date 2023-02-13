package com.example.asystentnauczyciela.ui.add_edit_grade

import com.example.asystentnauczyciela.data.entities.Course
import com.example.asystentnauczyciela.data.entities.Student
import com.example.asystentnauczyciela.data.relations.StudentWithCourses

sealed class AddEditGradeEvent {
    data class OnStudentClick(val studentWithCourses: StudentWithCourses): AddEditGradeEvent()
    data class OnSearchBarChange(val text: String): AddEditGradeEvent()
}
