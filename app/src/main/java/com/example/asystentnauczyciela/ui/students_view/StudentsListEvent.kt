package com.example.asystentnauczyciela.ui.students_view

import com.example.asystentnauczyciela.data.entities.Student

sealed class StudentsListEvent {
    data class OnDeleteStudentClick(val student: Student): StudentsListEvent()
    object OnUndoDeleteClick: StudentsListEvent()
    data class OnStudentClick(val student: Student): StudentsListEvent()
    object OnAddStudentClick: StudentsListEvent()
}
