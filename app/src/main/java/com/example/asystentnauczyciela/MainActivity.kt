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
import com.example.asystentnauczyciela.ui.add_edit_course.AddEditCourseScreen
import com.example.asystentnauczyciela.ui.add_edit_student.AddEditStudentScreen
import com.example.asystentnauczyciela.ui.course_with_student_view.CourseWithStudentListScreen
import com.example.asystentnauczyciela.ui.courses_view.CoursesListScreen
import com.example.asystentnauczyciela.ui.drawer_menu.AppBar
import com.example.asystentnauczyciela.ui.drawer_menu.DrawerBody
import com.example.asystentnauczyciela.ui.drawer_menu.DrawerHeader
import com.example.asystentnauczyciela.ui.drawer_menu.MenuItem
import com.example.asystentnauczyciela.ui.students_view.StudentsListScreen
import com.example.asystentnauczyciela.ui.theme.AssistantAppTheme
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
                                    route = Routes.COURSES_LIST
                                ),
                                MenuItem(
                                    id = "studenci",
                                    title = "Studenci",
                                    contentDescription = "Pokaż widok studenta",
                                    icon = ImageVector.vectorResource(id = R.drawable.group),
                                    route = Routes.STUDENTS_LIST
                                ),
                            ),
                            onItemClick = {
                               navController.navigate(it.route)
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                }
                            }
                        )
                    }
                ) {
                    Box {
                        NavHost(
                            navController = navController,
                            startDestination = Routes.COURSES_LIST,
                            ) {


                            //region Student

                            //region Studenci lista wszystkich

                            composable(route = Routes.STUDENTS_LIST) {
                                StudentsListScreen(
                                    onNavigate = {
                                        navController.navigate(it.route)
                                    }
                                )
                            }
                        //endregion

                            //region Studenci na zajęciach
//                            composable(
//                                route = Routes.STUDENT_LIST + "?classID={classID}",
//                                arguments = listOf(
//                                    navArgument("classID") {
//                                        type = NavType.StringType
////                                        defaultValue = "ID ZAJĘĆ DOMYŚLNY"
//                                        nullable = true
//                                    }
//                                )
//                            ) { entry ->
//                                StudentsListScreen(
//                                    onNavigate = {
//                                        navController.navigate(it.route)
//                                    },
//                                    classID = entry.arguments?.getString("classID")
//                                )
//                            }
                            //endregion

                            //region Widok Edycji/Dodawania Studentow
                            composable(
                                route = Routes.STUDENT_ADD_EDIT + "?studentId={studentId}",
                                arguments = listOf(
                                    navArgument(name = "studentId") {
                                        type = NavType.IntType
                                        defaultValue = -1
                                    }
                                )
                            ) {entry ->
                                AddEditStudentScreen(
                                    onPopBackStack = {
                                        navController.popBackStack()
                                    },
                                    studentId = entry.arguments!!.getInt("studentId")
                                )
                            }
                            //endregion

                            //endregion

                            //region Grade

//                            //region Widok ocen Studenta na zajęciach
//                            composable(
//                                route = Routes.GRADE_LIST + "/{classID}" + "/{studentID}",
//                                arguments = listOf(
//                                    navArgument("classID") {
//                                        type = NavType.StringType
//                                        defaultValue = "ID ZAJĘĆ DOMYŚLNY"
//                                    },
//                                    navArgument("studentID") {
//                                        type = NavType.StringType
//                                        defaultValue = "ID UCZNIA DOMYŚLNY"
//                                    }
//                                )
//                            ) { entry ->
//                                GradesListScreen(
//                                    onNavigate = {
//                                        navController.navigate(it.route)
//                                    },
//                                    classID = entry.arguments?.getString("classID").toString(),
//                                    studentID = entry.arguments?.getString("studentID").toString()
//                                )
//                            }
//                           //endregion

                            //endregion

                            //region Course

                            //region widok wszystkich przemiotow
                            composable(route = Routes.COURSES_LIST) {
                                CoursesListScreen(
                                    onNavigate = {
                                        navController.navigate(it.route)
                                    }
                                )
                            }

                            //endregion

                            //region Widok edycji / dodawnia przedmiotów
                            composable(
                                route = Routes.COURSE_ADD_EDIT + "?courseId={courseId}",
                                arguments = listOf(
                                    navArgument(name = "courseId") {
                                        type = NavType.IntType
                                        defaultValue = -1
                                    }
                                )
                            ) {entry ->
                                AddEditCourseScreen(
                                    onPopBackStack = {
                                        navController.popBackStack()
                                    },
                                    courseId = entry.arguments!!.getInt("courseId")
                                )
                            }

                            //endregion

                            //region widok Jednego przedmiotu z stuidentami
                            composable(
                                route = Routes.COURSE_STUDENT_LIST + "/{courseId}",
                                arguments = listOf(
                                    navArgument(name = "courseId") {
                                        type = NavType.IntType
                                    }
                                )
                            ) {entry ->
                                CourseWithStudentListScreen(
                                    onNavigate = {
                                        navController.navigate(it.route)
                                    },
                                    courseId = entry.arguments!!.getInt("courseId")
                                )
                            }

                            //endregion

                            //endregion

                        }
                    }
                }
            }
        }
    }
}