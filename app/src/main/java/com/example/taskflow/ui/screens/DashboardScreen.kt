package com.example.taskflow.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskflow.ui.components.NewTaskForm
import com.example.taskflow.ui.components.StatsRow
import com.example.taskflow.ui.components.TaskCard
import com.example.taskflow.ui.theme.Coral
import com.example.taskflow.ui.theme.Indigo
import com.example.taskflow.ui.theme.IndigoDark
import com.example.taskflow.ui.theme.Mint
import com.example.taskflow.viewModel.TaskFilter
import com.example.taskflow.viewModel.TaskViewModel

@Composable
fun DashboardScreen(
    darkTheme: Boolean,
    onToggleTheme: () -> Unit,
    viewModel: TaskViewModel = viewModel()
) {
    val selectedFilter = viewModel.selectedFilter
    val filteredTasks = viewModel.filteredTasks
    val showAddDialog = viewModel.showAddDialog

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(
                top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding(),
                bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 90.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.size(42.dp).clip(RoundedCornerShape(14.dp))
                        .background(Brush.linearGradient(listOf(Indigo, Coral))),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Outlined.AutoAwesome, contentDescription = "TaskFlow", tint = Color.White)
                }
                Column(modifier = Modifier.padding(start = 11.dp).weight(1f)) {
                    Text("TaskFlow", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
                    Text("Make space for what matters", fontSize = 11.sp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.58f))
                }
                IconButton(onClick = onToggleTheme) {
                    Icon(
                        if (darkTheme) Icons.Outlined.LightMode else Icons.Outlined.DarkMode,
                        contentDescription = if (darkTheme) "Switch to light theme" else "Switch to dark theme",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
                IconButton(onClick = {}) {
                    Icon(Icons.Outlined.NotificationsNone, contentDescription = "Notifications", tint = MaterialTheme.colorScheme.onBackground)
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(26.dp),
                    colors = CardDefaults.cardColors(containerColor = Indigo)
                ) {
                    Column(
                        modifier = Modifier.background(Brush.linearGradient(listOf(Indigo, IndigoDark))).padding(22.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text("TUESDAY, OCTOBER 20", color = Color.White.copy(alpha = 0.76f), fontSize = 10.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 1.2.sp)
                                Text("Good afternoon 👋", color = Color.White, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                            }
                            Box(
                                modifier = Modifier.size(54.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.15f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("✨", fontSize = 25.sp)
                            }
                        }
                        Text("You’re making great progress. Keep your momentum going!", color = Color.White.copy(alpha = 0.82f), fontSize = 13.sp)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text("Weekly progress", color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                                Text("${viewModel.completedTasksCount} of ${viewModel.totalTasksCount} tasks completed", color = Color.White.copy(alpha = 0.76f), fontSize = 11.sp)
                            }
                            Text("${viewModel.progressPercentage}%", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 22.sp)
                        }
                        LinearProgressIndicator(
                            progress = { viewModel.progressFraction },
                            modifier = Modifier.fillMaxWidth().clip(CircleShape),
                            color = Mint,
                            trackColor = Color.White.copy(alpha = 0.22f)
                        )
                    }
                }

                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Your overview", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
                        Spacer(Modifier.weight(1f))
                        Text("This week  ˅", fontSize = 12.sp, color = MaterialTheme.colorScheme.primary)
                    }
                    StatsRow(
                        totalCount = viewModel.totalTasksCount,
                        pendingCount = viewModel.pendingTasksCount,
                        completedCount = viewModel.completedTasksCount
                    )
                }

                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("My tasks", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
                            Text("A little progress each day adds up.", fontSize = 12.sp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.58f))
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FilterChip(
                            selected = selectedFilter == TaskFilter.TODAS,
                            onClick = { viewModel.selectedFilter = TaskFilter.TODAS },
                            label = { Text("Todas") }
                        )
                        FilterChip(
                            selected = selectedFilter == TaskFilter.PENDIENTES,
                            onClick = { viewModel.selectedFilter = TaskFilter.PENDIENTES },
                            label = { Text("Pendientes") }
                        )
                        FilterChip(
                            selected = selectedFilter == TaskFilter.COMPLETADAS,
                            onClick = { viewModel.selectedFilter = TaskFilter.COMPLETADAS },
                            label = { Text("Completadas") }
                        )
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(11.dp)) {
                        if (filteredTasks.isEmpty()) {
                            Text(
                                "No hay tareas en esta categoría.",
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                                fontSize = 13.sp,
                                modifier = Modifier.padding(vertical = 16.dp)
                            )
                        } else {
                            filteredTasks.forEach { task ->
                                TaskCard(
                                    task = task,
                                    onStatusChanged = { newStatus ->
                                        viewModel.updateTaskStatus(task.id, newStatus)
                                    },
                                    onDelete = {
                                        viewModel.deleteTask(task.id)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }

        ExtendedFloatingActionButton(
            onClick = { viewModel.showAddDialog = true },
            icon = { Icon(Icons.Outlined.Add, contentDescription = null) },
            text = { Text("Add task") },
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
        )

        if (showAddDialog) {
            NewTaskForm(
                onDismiss = { viewModel.showAddDialog = false },
                onAddTask = { title, category, priority ->
                    viewModel.addTask(title, category, priority)
                    viewModel.showAddDialog = false
                }
            )
        }
    }
}
