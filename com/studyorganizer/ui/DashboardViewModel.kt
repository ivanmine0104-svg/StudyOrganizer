package com.studyorganizer.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studyorganizer.data.StudyDao
import com.studyorganizer.data.SubjectEntity
import com.studyorganizer.data.TaskEntity
import com.studyorganizer.domain.CalculatePriorityUseCase
import com.studyorganizer.domain.StudyPriorityItem
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val dao: StudyDao,
    private val calculatePriorityUseCase: CalculatePriorityUseCase = CalculatePriorityUseCase()
) : ViewModel() {

    var priorityItems by mutableStateOf<List<StudyPriorityItem>>(emptyList())
        private set

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            // Datos simulados iniciales para demostración si la BD está vacía
            val subjects = listOf(
                SubjectEntity(id = 1, name = "Matemáticas", currentGrade = 55.0, colorHex = "#E57373"),
                SubjectEntity(id = 2, name = "Física", currentGrade = 75.0, colorHex = "#81C784")
            )
            
            val currentTime = System.currentTimeMillis()
            val tasks = listOf(
                TaskEntity(subjectId = 1, title = "Resolver guía de derivadas", dueDateMillis = currentTime + 86400000), // Mañana
                TaskEntity(subjectId = 2, title = "Estudiar vectores", dueDateMillis = currentTime + 432000000) // En 5 días
            )

            // Ejecutamos el algoritmo de priorización
            priorityItems = calculatePriorityUseCase.invoke(subjects, tasks)
        }
    }
}