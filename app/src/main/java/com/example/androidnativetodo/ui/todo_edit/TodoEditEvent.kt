package com.example.androidnativetodo.ui.todo_edit

sealed class TodoEditEvent {
  data class OnTitleChange(val value: String): TodoEditEvent()
  data class OnDescriptionChange(val value: String): TodoEditEvent()

  data class OnDeadlineChange(val value: String): TodoEditEvent()

  object OnSaveTodo: TodoEditEvent()

  object OnBackButtonTap: TodoEditEvent()
}
