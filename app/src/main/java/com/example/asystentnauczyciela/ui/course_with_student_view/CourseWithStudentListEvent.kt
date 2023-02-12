package com.example.asystentnauczyciela.ui.course_with_student_view

import com.example.asystentnauczyciela.data.entities.Student

sealed class CourseWithStudentListEvent {
    data class OnDeleteStudentClick(val student: Student): CourseWithStudentListEvent()
    object OnUndoDeleteClick: CourseWithStudentListEvent()
    data class OnStudentClick(val student: Student): CourseWithStudentListEvent()
    object OnAddStudentClick: CourseWithStudentListEvent()
}
