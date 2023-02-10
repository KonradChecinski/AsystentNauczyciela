package com.example.asystentnauczyciela

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.asystentnauczyciela.ui.add_edit_student.AddEditStudentScreen
import com.example.asystentnauczyciela.ui.add_edit_todo.AddEditTodoScreen
import com.example.asystentnauczyciela.ui.classes_view.ClassesListScreen
import com.example.asystentnauczyciela.ui.drawer_menu.AppBar
import com.example.asystentnauczyciela.ui.drawer_menu.DrawerBody
import com.example.asystentnauczyciela.ui.drawer_menu.DrawerHeader
import com.example.asystentnauczyciela.ui.drawer_menu.MenuItem
import com.example.asystentnauczyciela.ui.grades_view.GradesListScreen
import com.example.asystentnauczyciela.ui.students_view.StudentsListScreen
import com.example.asystentnauczyciela.ui.theme.AssistantAppTheme
import com.example.asystentnauczyciela.ui.todo_list.TodoListScreen
import com.example.asystentnauczyciela.util.Routes
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssistantAppTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        AppBar(
                            onNavigationIconClick = {
                                scope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }
                        )
                    },
//                    backgroundColor = Color(R.color.transparent),
                    drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
                    drawerContent = {
                        DrawerHeader()
                        DrawerBody(
                            items = listOf(
                                MenuItem(
                                    id = "zajecia",
                                    title = "Zajęcia",
                                    contentDescription = "Pokaż widok zajęć",
                                    icon = ImageVector.vectorResource(id = R.drawable.school),
                                    route = Routes.CLASS_LIST
                                ),
                                MenuItem(
                                    id = "studenci",
                                    title = "Studenci",
                                    contentDescription = "Pokaż widok studenta",
                                    icon = ImageVector.vectorResource(id = R.drawable.group),
                                    route = Routes.STUDENT_LIST
                                ),
                            ),
                            onItemClick = {
//                                when(it.route){
//                                    Routes.CLASS_LIST -> navController.navigate(it.route)
//                                    Routes.STUDENT_LIST -> navController.navigate(it.route + "/aloha")
//                                }
                               navController.navigate(it.route)
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                }

                            }
                        )
                    }
                ) {
                    Box {
//                        AppBar(
//                            onNavigationIconClick = {
//                                scope.launch {
//                                    scaffoldState.drawerState.open()
//                                }
//                            }
//                        )

                        NavHost(
                            navController = navController,
                            startDestination = Routes.CLASS_LIST,

                        ) {
//                            WIDOK PRZEDMIOTÓW
                            composable(route = Routes.CLASS_LIST) {
                                ClassesListScreen(
                                    onNavigate = {
                                        navController.navigate(it.route)
                                    }
                                )
                            }


//                            WIDOK STUDENTOW na zajecich
                            composable(
                                route = Routes.STUDENT_LIST + "?classID={classID}",
                                arguments = listOf(
                                    navArgument("classID") {
                                        type = NavType.StringType
//                                        defaultValue = "ID ZAJĘĆ DOMYŚLNY"
                                        nullable = true
                                    }
                                )
                            ) { entry ->
                                StudentsListScreen(
                                    onNavigate = {
                                        navController.navigate(it.route)
                                    },
                                    classID = entry.arguments?.getString("classID")
                                )
                            }


//                            WIDOK edycji-dodawania studentow
                            composable(
                                route = Routes.STUDENT_ADD_EDIT + "?studentId={studentId}",
                                arguments = listOf(
                                    navArgument(name = "studentId") {
                                        type = NavType.IntType
                                        defaultValue = -1
                                    }
                                )
                            ) {
                                AddEditStudentScreen(onPopBackStack = {
                                    navController.popBackStack()
                                })
                            }




                            composable(
                                route = Routes.GRADE_LIST + "/{classID}" + "/{studentID}",
                                arguments = listOf(
                                    navArgument("classID") {
                                        type = NavType.StringType
                                        defaultValue = "ID ZAJĘĆ DOMYŚLNY"
                                    },
                                    navArgument("studentID") {
                                        type = NavType.StringType
                                        defaultValue = "ID UCZNIA DOMYŚLNY"
                                    }
                                )
                            ) { entry ->
                                GradesListScreen(
                                    onNavigate = {
                                        navController.navigate(it.route)
                                    },
                                    classID = entry.arguments?.getString("classID").toString(),
                                    studentID = entry.arguments?.getString("studentID").toString()
                                )
                            }
                            composable(route = Routes.TODO_LIST) {
                                TodoListScreen(
                                    onNavigate = {
                                        navController.navigate(it.route)
                                    }
                                )
                            }
                            composable(
                                route = Routes.ADD_EDIT_TODO + "?todoId={todoId}",
                                arguments = listOf(
                                    navArgument(name = "todoId") {
                                        type = NavType.IntType
                                        defaultValue = -1
                                    }
                                )
                            ) {
                                AddEditTodoScreen(onPopBackStack = {
                                    navController.popBackStack()
                                })
                            }
                        }






                    }
                }
            }
        }
    }
}