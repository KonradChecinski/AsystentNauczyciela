package com.example.asystentnauczyciela

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
                    drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
                    drawerContent = {
                        DrawerHeader()

                        DrawerBody(
                            items = listOf(
                                MenuItem(
                                    id = "home",
                                    title = "Home",
                                    contentDescription = "Go to home screen",
                                    icon = Icons.Default.Home
                                ),
                                MenuItem(
                                    id = "settings",
                                    title = "Settings",
                                    contentDescription = "Go to settings screen",
                                    icon = Icons.Default.Settings
                                ),
                                MenuItem(
                                    id = "help",
                                    title = "Help",
                                    contentDescription = "Get help",
                                    icon = Icons.Default.Info
                                ),
                            ),
                            onItemClick = {
                                println("Clicked on ${it.title}")
                            }
                        )
                    }
                ) {

                }
                NavHost(
                    navController = navController,
                    startDestination = Routes.CLASS_LIST
                ) {
                    composable(route = Routes.CLASS_LIST) {
                        ClassesListScreen(
                            onNavigate = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                    composable(
                        route = Routes.STUDENT_LIST + "/{classID}",
                        arguments = listOf(
                            navArgument("classID") {
                                type = NavType.StringType
                                defaultValue = "ID ZAJĘĆ DOMYŚLNY"
                            }
                        )
                    ) { entry ->
                        StudentsListScreen(
                            onNavigate = {
                                navController.navigate(it.route)
                            },
                            classID = entry.arguments?.getString("classID").toString()
                        )
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
                                defaultValue= "ID UCZNIA DOMYŚLNY"
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