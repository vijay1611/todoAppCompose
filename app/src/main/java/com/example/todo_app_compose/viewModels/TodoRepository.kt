package com.example.movieappcompose.viewModels

import com.example.movieappcompose.Utils.RetrofitInstance
import com.example.todo_app_compose.models.Details
import com.example.todo_app_compose.models.TodoModel
import retrofit2.Response

class TodoRepository{

    suspend fun getTodoList(page:Int) : Response<TodoModel>{
        return  RetrofitInstance.api.getTodos(page)
    }
    suspend fun getDetailsById(id:Int) : Response<Details>{
        return  RetrofitInstance.api.getTodoById(id)
    }


}