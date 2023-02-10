package com.example.asystentnauczyciela.ui.add_edit_student

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.util.UiEvent
import kotlinx.coroutines.flow.collect

@Composable
fun AddEditStudentScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddEditStudentViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                }
                else -> Unit
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = MaterialTheme.colors.primary,
                onClick = {
                viewModel.onEvent(AddEditStudentEvent.OnSaveStudentClick)
            }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.save),
                    contentDescription = "Save"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TextField(
                value = viewModel.name,
                onValueChange = {
                    viewModel.onEvent(AddEditStudentEvent.OnNameChange(it))
                },
                placeholder = {
                    Text(text = "Imię")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = viewModel.lastname,
                onValueChange = {
                    viewModel.onEvent(AddEditStudentEvent.OnLastnameChange(it))
                },
                placeholder = {
                    Text(text = "Nazwisko")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 5
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = viewModel.albumNumber,
                onValueChange = {
                    viewModel.onEvent(AddEditStudentEvent.OnAlbumNumberChange(it))
                },
                placeholder = {
                    Text(text = "Numer albumu")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 5
            )
        }
    }
}