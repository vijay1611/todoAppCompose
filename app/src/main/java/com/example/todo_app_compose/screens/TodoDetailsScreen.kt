package com.example.todo_app_compose.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.movieappcompose.viewModels.TodoListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailsScreen(id:Int,onTextChange:(String)->Unit) {
    if(id==0){
        Toast.makeText(LocalContext.current, "Working", Toast.LENGTH_SHORT).show()
    }

    val movieViewModel = viewModel<TodoListViewModel>()

    movieViewModel.id = id
    movieViewModel.getDetailsById()
    val state = movieViewModel.state
    val details = state.detailsData
    var text1 by remember {
        mutableStateOf(details.todo)
    }
    var boolean1 by remember {
        mutableStateOf(details.completed)
    }
    var userId1 by remember {
        mutableStateOf(details.userId)
    }
    Scaffold(
        topBar = {TopBar()},
        content = {paddingValues->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Log.e("123", details.toString())
                var text2 = details.todo
                var boolean2 = details.completed
                var userId2 = details.userId
                text1 = text2
                OutlinedTextField(
                    modifier = Modifier.padding(20.dp),
                    label = { Text(text = "Todo")},
                    value = text1, onValueChange = {
                        text1 =it
                        onTextChange(it)
                    })
                OutlinedTextField(
                    modifier = Modifier.padding(20.dp),
                    label = { Text(text = "Completed or not")},
                    value =if(id==0){
                        ""
                    }else{ boolean2.toString()}, onValueChange = {
                        boolean1 =it.toBoolean()
                    })
                OutlinedTextField(
                    modifier = Modifier.padding(20.dp),
                    label = { Text(text = "User Id")},
                    value = if(id==0){
                        ""
                    }else{userId2.toString()}, onValueChange = {
                        userId1 =it.toInt()
                    })
                Button(onClick = {  },
                    modifier = Modifier.padding(20.dp)
                ) {
                    if(id==0){
                        Text(text = "Add Todo")
                    }else{
                        Text(text = "Update")
                    }
                }
            }

        }
    )


}

