package com.example.asystentnauczyciela.ui.add_edit_grade

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.*
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.ui.add_edit_course_with_student_view.AddEditCourseWithStudentEvent

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddEditGradeScreen(
    onPopBackStack: () -> Unit,
//    viewModel: AddEditCourseViewModel = hiltViewModel(),
    gradeId: Int
) {
    val scaffoldState = rememberScaffoldState()
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current


    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("2", "2.5", "3", "3.5", "4", "4.5", "5")
    var textfieldSize by remember { mutableStateOf(Size.Zero)}


//    val timePickerDialogFrom = TimePickerDialog(
//        LocalContext.current,
//        {_, hour:Int, minute: Int->
//            viewModel.onEvent(AddEditCourseEvent.OnTimeFromBlockChange(checkDigit(hour)+":"+checkDigit(minute)))
//        }, 0, 0, true
//    )
//    val timePickerDialogTo = TimePickerDialog(
//        LocalContext.current,
//        {_, hour:Int, minute: Int->
//            viewModel.onEvent(AddEditCourseEvent.OnTimeToBlockChange(checkDigit(hour)+":"+checkDigit(minute)))
//        }, 0, 0, true
//    )

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

//    LaunchedEffect(key1 = true) {
//        viewModel.uiEvent.collect { event ->
//            when(event) {
//                is UiEvent.PopBackStack -> onPopBackStack()
//                is UiEvent.ShowSnackbar -> {
//                    scaffoldState.snackbarHostState.showSnackbar(
//                        message = event.message,
//                        actionLabel = event.action
//                    )
//                }
//                else -> Unit
//            }
//        }
//    }
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = MaterialTheme.colors.primary,
                onClick = {
//                viewModel.onEvent(AddEditCourseEvent.OnSaveCourseClick)
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
            Text(
                text = if (gradeId == -1) "Dodaj ocenę" else "Edytuj ocenę",
                Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 10.dp),
                textAlign = TextAlign.Center,
                fontSize = 40.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row() {
                Text("Wpisz punkty: ")
                Checkbox(
                    checked = false,
                    onCheckedChange = {
//                        viewModel.onEvent(AddEditCourseWithStudentEvent.OnStudentClick(studentWithCourse))
                    },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.White)
                        .size(25.dp)
                        .align(Alignment.CenterVertically)
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            Column() {
                OutlinedTextField(
                    value = "ocena",
                    onValueChange = { /* viewModel.onEvent(AddEditCourseEvent.OnWeekDayChange(it)) */ },
                    readOnly=true,

//                    colors = TextFieldDefaults.outlinedTextFieldColors(
//                        disabledTextColor = LocalContentColor.current.copy(LocalContentAlpha.current),
//                        backgroundColor = TextFieldDefaults.textFieldColors().backgroundColor(enabled = true).value.copy(),
//                        disabledBorderColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
//                        disabledLabelColor = MaterialTheme.colors.primary,
//                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            //This value is used to assign to the DropDown the same width
                            textfieldSize = coordinates.size.toSize()
                        }
                        .clickable { expanded = !expanded },
                    label = { Text("Ocena") },
                    trailingIcon = {
                        Icon(icon, "contentDescription",
                            Modifier.clickable { expanded = !expanded })
                    }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
                ) {
                    suggestions.forEach { label ->
                        DropdownMenuItem(onClick = {
//                            viewModel.onEvent(AddEditCourseEvent.OnWeekDayChange(label))
                            expanded = false
                        }) {
                            Text(text = label)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}