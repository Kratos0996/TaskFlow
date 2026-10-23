package com.example.taskflow.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.PendingActions
import androidx.compose.material.icons.outlined.TaskAlt
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskflow.ui.theme.Indigo
import com.example.taskflow.ui.theme.Mint
import com.example.taskflow.ui.theme.Amber

@Composable
fun StatCard(label: String, value: String, icon: ImageVector, accent: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.surface).padding(13.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row {
                Icon(icon, contentDescription = null, tint = accent, modifier = Modifier.size(20.dp))
                Spacer(Modifier.weight(1f))
                Text(value, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onSurface)
            }
            Text(label, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.62f), fontSize = 12.sp)
        }
    }
}

@Composable
fun StatsRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        androidx.compose.foundation.layout.Box(Modifier.weight(1f)) {
            StatCard("Total tasks", "12", Icons.Outlined.TaskAlt, Indigo)
        }
        androidx.compose.foundation.layout.Box(Modifier.weight(1f)) {
            StatCard("Pending", "5", Icons.Outlined.PendingActions, Amber)
        }
        androidx.compose.foundation.layout.Box(Modifier.weight(1f)) {
            StatCard("Completed", "7", Icons.Outlined.CheckCircle, Mint)
        }
    }
}
