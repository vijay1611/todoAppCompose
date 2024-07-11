package com.example.movieappcompose.viewModels

import com.example.movieappcompose.Utils.RetrofitInstance
import com.example.todo_app_compose.models.TodoModel
import retrofit2.Response

class TodoRepository{

    suspend fun getTodoList(page:Int) : Response<TodoModel>{
        return  RetrofitInstance.api.getTodos(page)
    }


}