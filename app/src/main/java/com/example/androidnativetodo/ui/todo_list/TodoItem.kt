package com.example.androidnativetodo.ui.todo_list

import android.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    Checkbox(checked = todo.isDone, onCheckedChange = {
        isChecked -> onEvent(TodoListEvent.OnDoneChange(todo, isChecked))
    })
    Column (
      modifier = Modifier.weight(1f),
      verticalArrangement = Arrangement.Center
    ) {
      Text(
        text = todo.title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
      )
      Spacer(modifier = Modifier.width(4.dp))
      Text(
        text = todo.description,
        fontStyle = FontStyle.Italic,
        )
      Spacer(modifier = Modifier.width(4.dp))
      Text(
        text = todo.deadline,
        textDecoration = TextDecoration.Underline
        )
    }
    IconButton(onClick = {
      onEvent(TodoListEvent.DeleteTodo(todo))
    }) {
      Icon(
        imageVector = Icons.Default.Delete,
        tint = MaterialTheme.colors.error,
        contentDescription = "Delete")
    }
  }
}
