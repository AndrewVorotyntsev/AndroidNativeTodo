package com.example.androidnativetodo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// Data Access Object
// Опредеяет как мы будем взаимодействовть с данными
@Dao
interface TodoDao {
  // suspend - значит асинхронная операция

  // Стратегия на случай конфликта подразумевает редактирование существующей заметки
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertTodoItem(todoEntity: TodoEntity)

  @Delete
  suspend fun deleteTodoItem(todoEntity: TodoEntity)

  @Query("SELECT * FROM TodoEntity WHERE id = :id")
  suspend fun getTodoItemById(id: Int): TodoEntity?

  // Как только БД изменится это вызовет функцию
  @Query("SELECT * FROM TodoEntity")
  fun getTodos(): Flow<List<TodoEntity>>
}
