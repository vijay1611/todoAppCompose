package com.example.todo_app_compose


import com.example.todo_app_compose.models.TodoModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("todos?")
    suspend fun getTodos(
        @Query("page")
        page : Int
    ):Response<TodoModel>
}

