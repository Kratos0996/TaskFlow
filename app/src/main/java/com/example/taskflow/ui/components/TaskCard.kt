package com.example.taskflow.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.DesignServices
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.WorkOutline
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskflow.model.TaskItem
import com.example.taskflow.model.TaskPriority
import com.example.taskflow.model.TaskStatus
import com.example.taskflow.ui.theme.Amber
import com.example.taskflow.ui.theme.Coral
import com.example.taskflow.ui.theme.Indigo
import com.example.taskflow.ui.theme.Mint

@Composable
fun TaskCard(
    task: TaskItem,
    onStatusChanged: (TaskStatus) -> Unit,
    onDelete: () -> Unit
) {
    val priorityColor = when (task.priority) {
        TaskPriority.HIGH -> Coral
        TaskPriority.MEDIUM -> Amber
        TaskPriority.LOW -> Mint
    }
    val statusLabel = when (task.status) {
        TaskStatus.IN_PROGRESS -> "In progress"
        TaskStatus.TODO -> "To do"
        TaskStatus.COMPLETED -> "Completed"
    }
    val statusColor = when (task.status) {
        TaskStatus.IN_PROGRESS -> Indigo
        TaskStatus.TODO -> Amber
        TaskStatus.COMPLETED -> Mint
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface).padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = categoryIcon(task.icon),
                        contentDescription = null,
                        tint = Indigo,
                        modifier = Modifier.size(19.dp)
                    )
                    Spacer(Modifier.width(7.dp))
                    Text(task.category, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.62f), fontSize = 12.sp)
                    Spacer(Modifier.weight(1f))
                    Text("●", color = priorityColor, fontSize = 10.sp)
                    Spacer(Modifier.width(5.dp))
                    Text(
                        when (task.priority) {
                            TaskPriority.HIGH -> "High"
                            TaskPriority.MEDIUM -> "Medium"
                            TaskPriority.LOW -> "Low"
                        },
                        color = priorityColor,
                        fontSize = 12.sp
                    )
                    Spacer(Modifier.width(4.dp))
                    IconButton(
                        onClick = onDelete,
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            Icons.Outlined.Close,
                            contentDescription = "Delete task",
                            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        task.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.weight(1f)
                    )
                    Checkbox(
                        checked = task.status == TaskStatus.COMPLETED,
                        onCheckedChange = { checked ->
                            onStatusChanged(if (checked) TaskStatus.COMPLETED else TaskStatus.TODO)
                        }
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Outlined.AccessTime, contentDescription = null, tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f), modifier = Modifier.size(15.dp))
                    Spacer(Modifier.width(5.dp))
                    Text(task.dueLabel, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.58f), fontSize = 12.sp)
                    Spacer(Modifier.weight(1f))
                    Row(
                        modifier = Modifier.background(statusColor.copy(alpha = 0.13f), RoundedCornerShape(20.dp)).padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            if (task.status == TaskStatus.COMPLETED) Icons.Outlined.CheckCircle else Icons.Outlined.AccessTime,
                            contentDescription = null,
                            tint = statusColor,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(statusLabel, color = statusColor, fontSize = 11.sp)
                    }
                }
            }
        }
    }
}

private fun categoryIcon(key: String): ImageVector = when (key) {
    "wallet" -> Icons.Outlined.AccountBalanceWallet
    "design" -> Icons.Outlined.DesignServices
    "book" -> Icons.Outlined.MenuBook
    "health" -> Icons.Outlined.FitnessCenter
    else -> Icons.Outlined.WorkOutline
}
