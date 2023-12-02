package com.example.androidnativetodo.ui.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidnativetodo.data.TodoRepository
import com.example.androidnativetodo.utils.Routes
import com.example.androidnativetodo.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
  private val repository: TodoRepository
): ViewModel() {

  val todos = repository.getTodos()

  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  fun onEvent(event: TodoListEvent) {
    when(event) {
      is TodoListEvent.OnAddTodoTap -> {
        sendUiEvent(UiEvent.Navigate(Routes.TODO_EDIT))
      }
      is TodoListEvent.OnTodoTap -> {
        sendUiEvent(UiEvent.Navigate(Routes.TODO_EDIT + "?todoId=${event.todo.id}"))
      }
      is TodoListEvent.OnDoneChange -> {
        viewModelScope.launch {
          repository.insertTodoItem(
            event.todo.copy(isDone = event.isDone)
          )
        }
      }
      is TodoListEvent.DeleteTodo -> {
        viewModelScope.launch {
          repository.deleteTodoItem(event.todo)
        }
      }
    }
  }

  private fun sendUiEvent(event: UiEvent) {
    viewModelScope.launch {
      _uiEvent.send(event)
    }
  }
}