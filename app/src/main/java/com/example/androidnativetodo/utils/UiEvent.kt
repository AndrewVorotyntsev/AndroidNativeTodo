package com.example.androidnativetodo.utils

sealed class UiEvent{
  object  PopBackStack: UiEvent()
  data class Navigate(val route: String): UiEvent()
}
