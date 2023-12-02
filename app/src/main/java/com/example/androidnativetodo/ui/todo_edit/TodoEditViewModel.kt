package com.example.androidnativetodo.ui.todo_edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidnativetodo.data.TodoEntity
import com.example.androidnativetodo.data.TodoRepository
import com.example.androidnativetodo.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoEditViewModel @Inject constructor(
  private val repository: TodoRepository,
  // Для того чтобы восстановить предудщее состояния экрана списка
  savedStateHandle: SavedStateHandle
): ViewModel() {

  var todo by mutableStateOf<TodoEntity?>(null)
    private set

  var title by mutableStateOf("")
    private set

  var description by mutableStateOf("")
    private set

  var deadline by mutableStateOf("")
    private set

  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  init {
    // Получить id заметки из навигации
    val todoId = savedStateHandle.get<Int>("todoId")!!
    if (todoId != -1) {
      viewModelScope.launch {
        repository.getTodoItemById(todoId)?.let { todo ->
          title = todo.title
          description = todo.description
          deadline = todo.deadline
          this@TodoEditViewModel.todo = todo
        }
      }
    }
  }

  fun onEvent(event: TodoEditEvent) {
    when(event) {
      is TodoEditEvent.OnTitleChange -> {
        title = event.value
      }
      is TodoEditEvent.OnDescriptionChange -> {
        description = event.value
      }
      is TodoEditEvent.OnDeadlineChange -> {
        deadline = event.value
      }
      is TodoEditEvent.OnSaveTodo -> {
        viewModelScope.launch {
          if (title.isNotBlank() && description.isNotBlank() && deadline.isNotBlank()) {
            repository.insertTodoItem(
              TodoEntity(
                id = todo?.id,
                title = title,
                description = description,
                deadline = deadline,
                isDone = todo?.isDone ?: false,
              )
            )
            sendUiEvent(UiEvent.PopBackStack)
          }
        }
      }
      is TodoEditEvent.OnBackButtonTap -> {
        sendUiEvent(UiEvent.PopBackStack)
      }
    }
  }

  private fun sendUiEvent(event: UiEvent) {
    viewModelScope.launch {
      _uiEvent.send(event)
    }
  }
}