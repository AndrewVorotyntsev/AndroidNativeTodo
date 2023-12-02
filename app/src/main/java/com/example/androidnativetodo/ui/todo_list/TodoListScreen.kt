package com.example.androidnativetodo.ui.todo_list

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.androidnativetodo.utils.UiEvent
import kotlinx.coroutines.flow.collect

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TodoListScreen(
  onNavigate: (UiEvent.Navigate) -> Unit,
  viewModel: TodoListViewModel = hiltViewModel()
) {
  val todos = viewModel.todos.collectAsState(initial = emptyList())
  LaunchedEffect(key1 = true) {
    viewModel.uiEvent.collect {
      event -> when(event) {
        is UiEvent.Navigate -> {
          onNavigate(event)
        }
        else -> Unit
      }
    }
  }
  Scaffold(
    topBar = {
      TopAppBar(
        backgroundColor = Color.White,
        title = {
          Text("Заметки")
        }
      )
    },
    floatingActionButton = {
      FloatingActionButton(onClick = {
        viewModel.onEvent(TodoListEvent.OnAddTodoTap)
      }) {
        Icon(imageVector = Icons.Default.Edit, contentDescription = "Добавить заметку")
      }
    }
  ) {
    LazyColumn(
      modifier = Modifier.fillMaxSize()) {
      items(todos.value) { todo ->
        TodoItem(
          todo = todo,
          onEvent = viewModel::onEvent,
          modifier = Modifier
            .fillMaxWidth()
            .clickable {
              viewModel.onEvent(TodoListEvent.OnTodoTap(todo))
            }
            .padding(top=12.dp, start = 4.dp, end = 8.dp, bottom = 8.dp)
        )
      }
    }
  }
}