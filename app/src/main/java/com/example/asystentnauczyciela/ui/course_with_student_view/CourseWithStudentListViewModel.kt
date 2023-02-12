package com.example.asystentnauczyciela.ui.course_with_student_view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.data.entities.Student
import com.example.asystentnauczyciela.data.AssistantRepository
import com.example.asystentnauczyciela.util.Routes
import com.example.asystentnauczyciela.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseWithStudentListViewModel @Inject constructor(
    private val repository: AssistantRepository
): ViewModel() {
    var courseId: Int = 1
    var courseName: String = ""

    val students = repository.getStudents()
    val course = repository.getCourseById(courseId)?.let { course ->
        courseName = course.courseName
//        weekDay = course.weekDay.toString()
//        timeBlockFrom = course.timeBlockFrom
//        timeBlockTo = course.timeBlockTo
//        this@AddEditCourseViewModel.course = course
    }

    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedStudent: Student? = null

    fun onEvent(event: CourseWithStudentListEvent) {
        when(event) {
            is CourseWithStudentListEvent.OnStudentClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.STUDENT_ADD_EDIT + "?studentId=${event.student.studentId}"))
            }
            is CourseWithStudentListEvent.OnAddStudentClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.STUDENT_ADD_EDIT))
            }
            is CourseWithStudentListEvent.OnUndoDeleteClick -> {
                deletedStudent?.let { student ->
                    viewModelScope.launch {
                        repository.addStudent(student)
                    }
                }
            }
            is CourseWithStudentListEvent.OnDeleteStudentClick -> {
                viewModelScope.launch {
                    deletedStudent = event.student
                    repository.deleteStudent(event.student)
                    sendUiEvent(UiEvent.ShowSnackbar(
                        message = "Student usunięty",
                        action = "Cofnij"
                    ))
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}