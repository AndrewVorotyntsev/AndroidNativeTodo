package com.example.androidnativetodo.ui.todo_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androidnativetodo.data.TodoEntity
import com.example.androidnativetodo.utils.UiEvent

@Composable
fun TodoItem(
  todo: TodoEntity,
  onEvent: (TodoListEvent) -> Unit,
  modifier: Modifier = Modifier
) {
  Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Column (
      modifier = Modifier.weight(1f),
      verticalArrangement = Arrangement.Center
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(text = todo.title)
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(onClick = {
          onEvent(TodoListEvent.DeleteTodo(todo))
        }) {
          Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete")
        }
      }
      Spacer(modifier = Modifier.width(4.dp))
      Text(text = todo.description)
      Spacer(modifier = Modifier.width(4.dp))
      Text(text = todo.deadline)
    }
    Checkbox(checked = todo.isDone, onCheckedChange = {
      isChecked -> onEvent(TodoListEvent.OnDoneChange(todo, isChecked))
    })
  }
}
