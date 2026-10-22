package com.studyorganizer.data

import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GeminiService {
    
    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = "TU_API_KEY_AQUI"
    )

    suspend fun analyzeStudyTasks(taskDescription: String): String = withContext(Dispatchers.IO) {
        try {
            val prompt = "Actúa como un mentor académico experto. Analiza la siguiente carga de estudio y da recomendaciones breves y priorizadas: $taskDescription"
            val response = generativeModel.generateContent(prompt)
            response.text ?: "No se pudo generar una recomendación en este momento."
        } catch (e: Exception) {
            "Error de conexión con la IA: ${e.localizedMessage}"
        }
    }
}