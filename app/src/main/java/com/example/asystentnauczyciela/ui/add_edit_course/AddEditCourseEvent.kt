package com.example.asystentnauczyciela.ui.add_edit_course

sealed class AddEditCourseEvent {
    data class OnCourseNameChange(val courseName: String): AddEditCourseEvent()
    data class OnWeekDayChange(val weekDay: String): AddEditCourseEvent()
    data class OnTimeBlockChange(val timeBlock: String): AddEditCourseEvent()
    object OnSaveCourseClick: AddEditCourseEvent()
}
