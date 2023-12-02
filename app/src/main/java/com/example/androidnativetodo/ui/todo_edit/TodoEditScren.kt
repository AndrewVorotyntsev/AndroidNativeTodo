package com.example.androidnativetodo.ui.todo_edit

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.androidnativetodo.utils.UiEvent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TodoEditScreen(
  onPopBackStack: () -> Unit,
  viewModel: TodoEditViewModel = hiltViewModel()
) {
  LaunchedEffect(key1 = true) {
    viewModel.uiEvent.collect { event ->
      when (event) {
        is UiEvent.PopBackStack -> onPopBackStack()
        else -> Unit
      }
    }
  }
  Scaffold (
    topBar = {
      TopAppBar(
        backgroundColor = Color.White,
        title = {
          if (viewModel.todo == null)
            Text("Новая заметка")
          else Text("Редактировать заметку")
        },
        navigationIcon = {
          IconButton(
            onClick = {
              viewModel.onEvent(TodoEditEvent.OnBackButtonTap)
            }
          ) {
            Icon(
              imageVector = Icons.Filled.ArrowBack,
              contentDescription = "Back"
            )
          }
        }
      )
    },
    modifier = Modifier
      .fillMaxSize(),
  ) {
    Column(
      modifier = Modifier.fillMaxSize().padding(16.dp),
    ) {
      TextField(
        value = viewModel.title,
        onValueChange = {
        viewModel.onEvent(TodoEditEvent.OnTitleChange(it))
        },
        placeholder = {
          Text(text = "Заголовок")
        },
        modifier = Modifier.fillMaxWidth()
      )
      Spacer(modifier = Modifier.height(8.dp))
      TextField(
        value = viewModel.description,
        onValueChange = {
          viewModel.onEvent(TodoEditEvent.OnDescriptionChange(it))
        },
        placeholder = {
          Text(text = "Описание")
        },
        modifier = Modifier.fillMaxWidth(),
        maxLines = 10
      )
      Spacer(modifier = Modifier.height(8.dp))
      TextField(
        value = viewModel.deadline,
        onValueChange = {
          viewModel.onEvent(TodoEditEvent.OnDeadlineChange(it))
        },
        placeholder = {
          Text(text = "Срок выполнения")
        },
        modifier = Modifier.fillMaxWidth()
      )
      Spacer(modifier = Modifier.height(8.dp))
      Button(onClick = {
        viewModel.onEvent(TodoEditEvent.OnSaveTodo)
      }) {
        Text(text = "Сохранить")
        Icon(imageVector = Icons.Default.Done, contentDescription = "Сохранить")
      }
    }
  }

}