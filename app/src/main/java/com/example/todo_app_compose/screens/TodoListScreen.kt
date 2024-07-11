package com.example.todo_app_compose.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movieappcompose.viewModels.TodoListViewModel
import com.example.todo_app_compose.models.Todo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(navHostController: NavHostController) {
    val vm = viewModel<TodoListViewModel>()
    val state = vm.state
    Scaffold(
        modifier = Modifier.background(Color.Red),
        topBar = {TopBar()},
        content = { paddingValues ->
            LazyVerticalGrid(columns = GridCells.Fixed(1),
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(Color.Transparent),
                content = {
                    itemsIndexed(
                        items = state.todoList,
                    ){index,it->
                        ItemUi(itemIndex=index, todoList = state.todoList,navController=navHostController)
                        if(index>0){
                            Log.e("TAG", "HomeScreen1: $index", )
                        }
                    }
                }
            )
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemUi(itemIndex: Int, todoList: List<Todo>, navController: NavHostController) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(10.dp)
            .clickable { },
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .background(Color.LightGray.copy(.7f))
                    .padding(6.dp)
            ) {
                Text(
                    text ="${todoList[itemIndex].id}) ${todoList[itemIndex].todo}",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        shadow = Shadow(
                            Color(0xFFFC6603), offset = Offset(1f, 1f), 3f
                        )
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                    ) {
                        Text(
                            text = "Completed: ",
                            textAlign = TextAlign.Start,
                            color = Color.Black
                        )

                        Text(
                            text = todoList[itemIndex].completed.toString(),
                            textAlign = TextAlign.End,
                            fontWeight = FontWeight.Bold,
                            color = Color.Blue
                        )
                    }
                    Row(
                    ) {
                        Text(
                            text = "User Id: ",
                            textAlign = TextAlign.Start,
                            color = Color.Black
                        )

                        Text(
                            text = todoList[itemIndex].userId.toString(),
                            textAlign = TextAlign.End,
                            fontWeight = FontWeight.Bold,
                            color = Color.Blue
                        )
                    }
                }
            }

        }
    }
}


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopBar() {
        TopAppBar(
            title = { Text(text = "Todo App") },
            colors = TopAppBarDefaults.largeTopAppBarColors(
                containerColor = Color.White.copy(.7f)
            )
        )
    }
