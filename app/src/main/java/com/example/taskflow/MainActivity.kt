package com.example.taskflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.taskflow.ui.screens.DashboardScreen
import com.example.taskflow.viewModel.TaskViewModel
import com.example.taskflow.ui.theme.TaskFlowTheme

class MainActivity : ComponentActivity() {
    private val taskViewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var darkTheme by rememberSaveable { mutableStateOf(false) }
            TaskFlowTheme(darkTheme = darkTheme) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    DashboardScreen(
                        darkTheme = darkTheme,
                        onToggleTheme = { darkTheme = !darkTheme },
                        viewModel = taskViewModel
                    )
                }
            }
        }
    }
}
