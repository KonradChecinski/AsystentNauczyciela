package com.example.asystentnauczyciela.ui.grades_view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.asystentnauczyciela.ui.todo_list.TodoListViewModel
import com.example.asystentnauczyciela.util.UiEvent

@Composable
fun GradesListScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    classID: String,
    studentID: String,
    modifier: Modifier = Modifier,
    viewModel: TodoListViewModel = hiltViewModel()
) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Widok ocen dla ucznia w zajęciach")
        Text(classID)
        Text(studentID)
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = {}
        ) {
            Text("Continue")
        }
    }
}