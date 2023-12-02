package com.example.androidnativetodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.androidnativetodo.ui.theme.AndroidNativeTodoTheme
import com.example.androidnativetodo.ui.todo_edit.TodoEditScreen
import com.example.androidnativetodo.ui.todo_list.TodoListScreen
import com.example.androidnativetodo.utils.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      AndroidNativeTodoTheme {
        val navController = rememberNavController()
        NavHost(
          navController = navController,
          startDestination = Routes.TODO_LIST
        ) {
          composable(Routes.TODO_LIST) {
            TodoListScreen(onNavigate = {
              navController.navigate(it.route)
            })
          }
          composable(
            route = Routes.TODO_EDIT + "?todoId={todoId}",
            arguments = listOf(
              navArgument(name = "todoId") {
                type = NavType.IntType
                defaultValue = -1
              }
            )
            ) {
            TodoEditScreen(onPopBackStack = {
              navController.popBackStack()
            })
          }
        }
      }
    }
  }
}
