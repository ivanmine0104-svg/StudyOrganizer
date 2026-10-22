package com.studyorganizer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studyorganizer.data.GeminiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val geminiService = GeminiService()

    private val _aiRecommendation = MutableStateFlow("Presiona el botón para analizar tus materias con IA.")
    val aiRecommendation: StateFlow<String> = _aiRecommendation.asStateFlow()

    fun analyzeTasksWithAI(taskSummary: String) {
        viewModelScope.launch {
            _aiRecommendation.value = "Analizando tu carga académica con Gemini..."
            val result = geminiService.analyzeStudyTasks(taskSummary)
            _aiRecommendation.value = result
        }
    }
}