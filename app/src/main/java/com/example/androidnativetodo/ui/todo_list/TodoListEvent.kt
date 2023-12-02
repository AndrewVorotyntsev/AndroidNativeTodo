package com.example.androidnativetodo.ui.todo_list

import com.example.androidnativetodo.data.TodoEntity

sealed class TodoListEvent {
  data class DeleteTodo(val todo: TodoEntity): TodoListEvent()
  data class OnDoneChange(val todo: TodoEntity, val isDone: Boolean): TodoListEvent()
  data class OnTodoTap(val todo: TodoEntity) : TodoListEvent()
  object OnAddTodoTap: TodoListEvent()
}
