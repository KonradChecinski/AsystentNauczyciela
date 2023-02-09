package com.example.asystentnauczyciela

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.asystentnauczyciela.ui.add_edit_todo.AddEditTodoScreen
import com.example.asystentnauczyciela.ui.classes_view.ClassesListScreen
import com.example.asystentnauczyciela.ui.grades_view.GradesListScreen
import com.example.asystentnauczyciela.ui.students_view.StudentsListScreen
import com.example.asystentnauczyciela.ui.theme.AssistantAppTheme
import com.example.asystentnauczyciela.ui.todo_list.TodoListScreen
import com.example.asystentnauczyciela.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssistantAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.GRADE_LIST
                ) {
                    composable(Routes.CLASS_LIST) {
                        ClassesListScreen(
                            onNavigate = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                    composable(Routes.STUDENT_LIST) {
                        StudentsListScreen(
                            onNavigate = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                    composable(Routes.GRADE_LIST) {
                        GradesListScreen(
                            onNavigate = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                    composable(Routes.TODO_LIST) {
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