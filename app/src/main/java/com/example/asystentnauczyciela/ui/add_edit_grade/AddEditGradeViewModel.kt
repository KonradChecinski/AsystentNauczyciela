package com.example.asystentnauczyciela.ui.add_edit_grade

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.data.entities.Student
import com.example.asystentnauczyciela.data.AssistantRepository
import com.example.asystentnauczyciela.data.entities.Course
import com.example.asystentnauczyciela.data.relations.StudentWithCourses
import com.example.asystentnauczyciela.util.Routes
import com.example.asystentnauczyciela.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditGradeViewModel @Inject constructor(
    private val repository: AssistantRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    var gradeId: Int? = savedStateHandle.get("gradeId")

    var text by mutableStateOf("")
        private set


    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()




    fun onEvent(event: AddEditGradeEvent) {
        when(event) {
            is AddEditGradeEvent.OnStudentClick -> {
//                viewModelScope.launch {
//                    if (event.studentWithCourses.courses.any{ course -> course.courseId == gradeId}){
//                        repository.deleteStudentWithCourse(gradeId!!, event.studentWithCourses.student.studentId!!)
//                    }else{
//                        repository.addStudentWithCourse(gradeId!!, event.studentWithCourses.student.studentId!!)
//                    }
//                }
            }
            is AddEditGradeEvent.OnSearchBarChange -> {
//                text = event.text
//                studentsWithCourse = repository.getStudentsWithCourseSearch(text)
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}