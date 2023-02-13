package com.example.asystentnauczyciela.ui.course_with_student_view

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.data.entities.Course
import com.example.asystentnauczyciela.data.entities.Student
import com.example.asystentnauczyciela.data.relations.CourseWithStudents
import com.example.asystentnauczyciela.util.Routes
import com.example.asystentnauczyciela.util.UiEvent
import kotlinx.coroutines.flow.collect

@Composable
fun CourseWithStudentListScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    modifier: Modifier = Modifier,
    courseId: Int,
    viewModel: CourseWithStudentListViewModel = hiltViewModel()
) {

    val iconSize = 36.dp
    val offsetInPx = LocalDensity.current.run { (iconSize / 2).roundToPx() }

    val course = viewModel.course.collectAsState(initial = CourseWithStudents(
        Course(0,"","","", ""),
        emptyList()
        )
    )
//    val students = course.value.

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.ShowSnackbar -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                    if(result == SnackbarResult.ActionPerformed) {
//                        viewModel.onEvent(CourseWithStudentListEvent.OnUndoDeleteClick)
                    }
                }
                is UiEvent.Navigate -> onNavigate(event)
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
                    onNavigate(UiEvent.Navigate(route = Routes.COURSE_STUDENT_LIST_ADD_EDIT + "/" + course.value.course.courseId))
                }) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Add student to course"
                )
            }
        }
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
        ) {
            Text(
                text = course.value.course.courseName,
                modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 10.dp),
                textAlign = TextAlign.Center,
                fontSize = 40.sp
            )
            Text(
                text = "Studenci: ",
                modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp, 0.dp, 10.dp),
                textAlign = TextAlign.Left,
                fontSize = 15.sp
            )
            LazyColumn(
                modifier.fillMaxSize()
            ) {
                items(items = course.value.students){
                    Box{
                        Card(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                                .clickable {
//                                    viewModel.onEvent(CourseWithStudentListEvent.OnStudentClick(it))
                                },
                            elevation = 3.dp,
                            shape = RoundedCornerShape(10.dp),
                        ) {
                            Row(
                                modifier = modifier
                                    .fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.person48),
                                    contentDescription = "Zapisz",
                                    modifier.padding(20.dp),
                                )
                                Column(
                                    modifier = modifier.fillMaxHeight(),
                                    verticalArrangement = Arrangement.Center,
                                ) {
                                    Text(text = it.name + " " + it.lastName, modifier.padding(1.dp, 5.dp, 0.dp, 1.dp))
                                    Text(text = "Numer albumu: " + it.albumNumber, modifier.padding(1.dp,0.dp, 5.dp, 0.dp))
                                    Text(text = "Oceny: ", modifier.padding(1.dp,0.dp, 5.dp, 0.dp))

                                    Row() {
                                        Card(
                                            modifier = modifier
                                                .padding(0.dp, 10.dp, 10.dp, 10.dp)
                                                .clickable { onNavigate(UiEvent.Navigate(route = Routes.GRADE_ADD_EDIT)) },
                                            elevation = 3.dp,
                                            shape = CutCornerShape(5.dp)
                                        ) {
                                            Text(text = "5", modifier = modifier.padding(10.dp))
                                        }
                                        Card(
                                            modifier = modifier
                                                .padding(0.dp, 10.dp, 10.dp, 10.dp)
                                                .clickable { onNavigate(UiEvent.Navigate(route = Routes.GRADE_ADD_EDIT)) },
                                            elevation = 3.dp,
                                            shape = CutCornerShape(10.dp)
                                        ) {
                                            Text(text = "3.5", modifier = modifier.padding(10.dp))
                                        }
                                    }
                                }
                            }
                        }
                        IconButton(
                            onClick = {
                                onNavigate(UiEvent.Navigate(route = Routes.GRADE_ADD_EDIT))
                            },
                            modifier = Modifier
                                .offset {
                                    IntOffset(x = -offsetInPx, y = -offsetInPx)
                                }
                                .clip(CircleShape)
                                .background(MaterialTheme.colors.background)
                                .size(iconSize)
                                .align(Alignment.BottomEnd)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add grade",
                            )
                        }
                    }

                }
            }
        }
    }


}