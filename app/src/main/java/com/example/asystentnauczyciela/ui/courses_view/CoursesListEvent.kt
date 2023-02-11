package com.example.asystentnauczyciela.ui.courses_view

import com.example.asystentnauczyciela.data.entities.Course

sealed class CoursesListEvent {
    data class OnDeleteCourseClick(val course: Course): CoursesListEvent()
    object OnUndoDeleteClick: CoursesListEvent()
    data class OnCourseClick(val course: Course): CoursesListEvent()
    object OnAddCourseClick: CoursesListEvent()
}
