//package com.example.asystentnauczyciela.ui.courses_view
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.Button
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.example.asystentnauczyciela.ui.theme.AssistantAppTheme
//import com.example.asystentnauczyciela.ui.todo_list.TodoListViewModel
//import com.example.asystentnauczyciela.util.Routes
//import com.example.asystentnauczyciela.util.UiEvent
//
//@Composable
//fun ClassesListScreenStare(
//    onNavigate: (UiEvent.Navigate) -> Unit,
//    viewModel: TodoListViewModel = hiltViewModel(),
//    modifier: Modifier = Modifier
//) {
//
//    Column(
//        modifier = modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text("Widok zajęć")
//        Button(
//            modifier = Modifier.padding(vertical = 24.dp),
//            onClick = {onNavigate(UiEvent.Navigate(route = Routes.STUDENT_LIST + "/ID_ZAJĘĆ"))}
//        ) {
//            Text("Widok uczniów")
//        }
//    }
//}