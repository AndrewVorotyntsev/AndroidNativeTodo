package com.example.androidnativetodo.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class TodoEntity(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val description: String,
    val isDone: Boolean,
    val deadline: String,
)
