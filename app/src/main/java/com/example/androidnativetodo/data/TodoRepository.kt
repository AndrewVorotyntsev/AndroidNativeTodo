package com.example.androidnativetodo.data

import kotlinx.coroutines.flow.Flow

interface TodoRepository {

  suspend fun insertTodoItem(todoEntity: TodoEntity)

  suspend fun deleteTodoItem(todoEntity: TodoEntity)

  suspend fun getTodoItemById(id: Int): TodoEntity?

  fun getTodos(): Flow<List<TodoEntity>>
}