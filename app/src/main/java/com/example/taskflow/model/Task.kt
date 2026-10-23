package com.example.taskflow.model

enum class TaskPriority { HIGH, MEDIUM, LOW }
enum class TaskStatus { IN_PROGRESS, TODO, COMPLETED }

data class TaskItem(
    val id: Int,
    val title: String,
    val category: String,
    val priority: TaskPriority,
    val status: TaskStatus,
    val dueLabel: String,
    val icon: String
)
