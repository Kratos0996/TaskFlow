package com.example.taskflow.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.taskflow.data.sampleTasks
import com.example.taskflow.model.TaskItem
import com.example.taskflow.model.TaskPriority
import com.example.taskflow.model.TaskStatus

enum class TaskFilter { TODAS, PENDIENTES, COMPLETADAS }

class TaskViewModel : ViewModel() {
    private val _tasks = mutableStateListOf<TaskItem>().apply { addAll(sampleTasks) }
    val tasks: List<TaskItem> get() = _tasks

    var selectedFilter by mutableStateOf(TaskFilter.TODAS)
    var showAddDialog by mutableStateOf(false)

    val filteredTasks: List<TaskItem>
        get() = when (selectedFilter) {
            TaskFilter.TODAS -> _tasks
            TaskFilter.PENDIENTES -> _tasks.filter { it.status == TaskStatus.TODO || it.status == TaskStatus.IN_PROGRESS }
            TaskFilter.COMPLETADAS -> _tasks.filter { it.status == TaskStatus.COMPLETED }
        }

    val totalTasksCount: Int get() = _tasks.size
    val completedTasksCount: Int get() = _tasks.count { it.status == TaskStatus.COMPLETED }
    val pendingTasksCount: Int get() = _tasks.count { it.status == TaskStatus.TODO || it.status == TaskStatus.IN_PROGRESS }
    val progressFraction: Float get() = if (_tasks.isNotEmpty()) completedTasksCount.toFloat() / _tasks.size.toFloat() else 0f
    val progressPercentage: Int get() = if (_tasks.isNotEmpty()) (completedTasksCount * 100) / _tasks.size else 0

    fun addTask(title: String, category: String, priority: TaskPriority) {
        val newId = (_tasks.maxOfOrNull { it.id } ?: 0) + 1
        val newItem = TaskItem(
            id = newId,
            title = title,
            category = category,
            priority = priority,
            status = TaskStatus.TODO,
            dueLabel = "Hoy",
            icon = when (category.lowercase()) {
                "personal" -> "wallet"
                "design" -> "design"
                "learning" -> "book"
                "health" -> "health"
                else -> "work"
            }
        )
        _tasks.add(newItem)
    }

    fun updateTaskStatus(taskId: Int, newStatus: TaskStatus) {
        val index = _tasks.indexOfFirst { it.id == taskId }
        if (index != -1) {
            _tasks[index] = _tasks[index].copy(status = newStatus)
        }
    }

    fun deleteTask(taskId: Int) {
        _tasks.removeIf { it.id == taskId }
    }
}
