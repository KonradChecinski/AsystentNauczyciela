package com.example.asystentnauczyciela.ui.add_edit_grade

import com.example.asystentnauczyciela.data.relations.StudentWithCourses

sealed class AddEditGradeEvent {
    data class OnStudentClick(val studentWithCourses: StudentWithCourses): AddEditGradeEvent()
    data class OnCheckBoxClick(val isPoints: Boolean): AddEditGradeEvent()
    data class OnPointsChange(val text: String): AddEditGradeEvent()
    data class OnGradeChange(val text: String): AddEditGradeEvent()

    object OnSaveGradeClick: AddEditGradeEvent()
    object OnDeleteGradeClick: AddEditGradeEvent()
    object OnConfirmDeleteGradeClick: AddEditGradeEvent()
}
