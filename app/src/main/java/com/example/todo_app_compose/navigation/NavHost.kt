package com.example.todo_app_compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todo_app_compose.screens.TodoDetailsScreen
import com.example.todo_app_compose.screens.TodoListScreen

@Composable
fun Navigation() {
        var navController = rememberNavController()
    NavHost(navController = navController, startDestination = "List Screen"){
        composable("List Screen"){
            TodoListScreen(navController)
        }
        composable("Detail Screen"){
            TodoDetailsScreen(navController)
        }
    }
}


