package com.example.todo_app_compose.models

data class Todo(
    val completed: Boolean,
    val id: Int,
    val todo: String,
    val userId: Int
)