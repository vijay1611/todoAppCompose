package com.example.todo_app_compose.models

data class TodoModel(
    val limit: Int,
    val skip: Int,
    val todos: List<Todo>,
    val total: Int
)