package com.example.taskflow.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.taskflow.model.TaskItem

class TaskLocalStorage(context: Context) {
    private val prefs = context.getSharedPreferences("task_flow_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val tasksKey = "saved_tasks_list"

    fun saveTasks(tasks: List<TaskItem>) {
        val json = gson.toJson(tasks)
        prefs.edit().putString(tasksKey, json).apply()
    }

    fun loadTasks(): List<TaskItem>? {
        val json = prefs.getString(tasksKey, null) ?: return null
        val type = object : TypeToken<List<TaskItem>>() {}.type
        return try {
            gson.fromJson(json, type)
        } catch (e: Exception) {
            null
        }
    }
}
