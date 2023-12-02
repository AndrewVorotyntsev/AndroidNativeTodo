package com.example.androidnativetodo.data

import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(private val dao: TodoDao): TodoRepository {

  override suspend fun insertTodoItem(todoEntity: TodoEntity) {
    dao.insertTodoItem(todoEntity)
  }

  override suspend fun deleteTodoItem(todoEntity: TodoEntity) {
    dao.deleteTodoItem(todoEntity)
  }

  override suspend fun getTodoItemById(id: Int): TodoEntity? {
    return dao.getTodoItemById(id)
  }

  override fun getTodos(): Flow<List<TodoEntity>> {
    return dao.getTodos()
  }
}