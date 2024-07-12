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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movieappcompose.viewModels.TodoListViewModel
import com.example.todo_app_compose.R
import com.example.todo_app_compose.models.Todo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(navHostController: NavHostController,onNavigate: (String) -> Unit) {
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
        },
        floatingActionButton = {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                FloatingActionButton(
                    onClick = {
                              navHostController.navigate("Details Screen/${0}")
                    },
                    containerColor = Color.Blue,
                    contentColor = Color.White
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Add"
                    )
                }
            }
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val vm = viewModel<TodoListViewModel>()
        val state = vm.state
        var searchMovie by remember {
            mutableStateOf(listOf<Todo>())
        }
        var movie by remember {
            mutableStateOf("")
        }

        val keyBoardController = LocalSoftwareKeyboardController.current
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = movie,
            onValueChange ={
                movie = it
            },
            label = {Text(text ="Search for any movies" ) })
        IconButton(onClick = {
            searchMovie = if(movie.isEmpty()){
                listOf()
            }else{
                vm.state.todoList.filter {
                    it.todo.startsWith(movie, true)
                }
            }

            Log.e("search11", movie.toString() )
            Log.e("search11", searchMovie.toString() )
            //isSearch = true
            keyBoardController?.hide()}
        ) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search for amy location")

        }
    }

}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemUi(itemIndex: Int, todoList: List<Todo>, navController: NavHostController) {
    Card(
        modifier = Modifier
            .width(380.dp)
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
            ) {
            Column(
                modifier = Modifier
                    .background(Color.LightGray.copy(.7f))
                    .padding(6.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Image(imageVector = Icons.Default.Edit, contentDescription = "",
                    Modifier.clickable {
                        navController.navigate("Details Screen/${todoList[itemIndex].id}")
                    })
                Image(imageVector = Icons.Default.Delete, contentDescription = "")
            }

            Column(
                modifier = Modifier
                    .width(350.dp)
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
                containerColor = Color.Blue/*.copy(.3f)*/,
                titleContentColor = Color.White
            )
        )
    }
