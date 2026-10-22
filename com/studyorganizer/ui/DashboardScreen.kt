package com.studyorganizer.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Modelo visual simulado de las sesiones de estudio
data class DailySessionUi(
    val timeSlot: String,
    val subjectName: String,
    val reason: String,
    val color: Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onUploadClick: () -> Unit = {},
    onEditTempScheduleClick: () -> Unit = {}
) {
    // Sesiones sugeridas por el algoritmo de priorización
    val todaySessions = listOf(
        DailySessionUi("6:00 PM - 8:00 PM", "Matemáticas", "Tarea vence mañana (Urgencia Máxima)", Color(0xFFE57373)),
        DailySessionUi("9:00 PM - 10:00 PM", "Física", "Repaso por promedio de nota bajo (55%)", Color(0xFF81C784))
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Organizador Inteligente", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onUploadClick,
                icon = { Icon(Icons.Default.Add, contentDescription = "Subir") },
                text = { Text("Subir Apuntes / Exámenes") },
                containerColor = MaterialTheme.colorScheme.primary
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Ajuste temporal de horario
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Horario de Hoy", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text(
                                "Usando plantilla fija. ¿Tienes más/menos tiempo hoy?",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Button(
                            onClick = onEditTempScheduleClick,
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
                        ) {
                            Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Ajustar hoy", fontSize = 12.sp)
                        }
                    }
                }
            }

            item {
                Text(
                    text = "Tu Itinerario Sugerido",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            items(todaySessions) { session ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = session.color.copy(alpha = 0.15f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = session.subjectName,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = session.color
                            )
                            Text(
                                text = session.timeSlot,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = session.reason,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}