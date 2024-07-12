package com.example.todo_app_compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todo_app_compose.screens.TodoDetailsScreen
import com.example.todo_app_compose.screens.TodoListScreen

@Composable
fun Navigation() {
        var navController = rememberNavController()
    NavHost(navController = navController, startDestination = "List Screen"){
        composable("List Screen"){
            TodoListScreen(navController, onNavigate = {})
        }
        composable("Details Screen/{id}",
        arguments = listOf(
            navArgument("id"){
                type = NavType.IntType
            }
        )) { id ->
            id.arguments?.getInt("id")?.let { id1 ->
                TodoDetailsScreen(id=id1, onTextChange = {})
            }
        }
    }
}


