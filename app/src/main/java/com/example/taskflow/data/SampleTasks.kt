package com.example.taskflow.data

import com.example.taskflow.model.TaskItem
import com.example.taskflow.model.TaskPriority
import com.example.taskflow.model.TaskStatus

val sampleTasks = listOf(
    TaskItem(1, "Finalize project proposal", "Work", TaskPriority.HIGH, TaskStatus.IN_PROGRESS, "Today · 2:30 PM", "work"),
    TaskItem(2, "Review monthly budget", "Personal", TaskPriority.MEDIUM, TaskStatus.TODO, "Today · 5:00 PM", "wallet"),
    TaskItem(3, "Prepare UI presentation", "Design", TaskPriority.HIGH, TaskStatus.IN_PROGRESS, "Tomorrow · 10:00 AM", "design"),
    TaskItem(4, "Read 20 pages", "Learning", TaskPriority.LOW, TaskStatus.COMPLETED, "Completed yesterday", "book"),
    TaskItem(5, "Plan weekend workout", "Health", TaskPriority.LOW, TaskStatus.TODO, "Fri · 8:00 AM", "health")
)
