package com.example.movieappcompose.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_app_compose.models.Todo
import kotlinx.coroutines.launch

class TodoListViewModel : ViewModel() {

    var repo = TodoRepository()
    var state by mutableStateOf(ScreenState())


    var currentPage = 1
    var loading : Boolean= false

    init {
        viewModelScope.launch {
           val response = repo.getTodoList(state.page)
            state = state.copy(
                todoList = response.body()!!.todos,
                page = state.page
            )
        }
    }

    fun nextPageCall(){
        if(!loading){
            loading=true
            currentPage++
            getMovieNextPage(currentPage)
        }
    }


    private fun getMovieNextPage(page: Int){
        viewModelScope.launch {
            val response = repo.getTodoList(page)
            val arrlist = state.todoList.toMutableStateList()
            response.body()?.todos?.let { arrlist.addAll(it)}
            state = state.copy(
                todoList = arrlist
            )
            loading=false
        }
    }
}

data class ScreenState(
    var todoList : List<Todo> = emptyList(),
    var page : Int = 10
)