package com.studyorganizer.domain

import com.studyorganizer.data.SubjectEntity
import com.studyorganizer.data.TaskEntity

data.class StudyPriorityItem(
    val subjectName: String,
    val taskTitle: String,
    val priorityScore: Double,
    val reason: String
)

class CalculatePriorityUseCase {

    // Algoritmo que calcula la prioridad combinando Urgencia y Gravedad Académica
    fun invoke(subjects: List<SubjectEntity>, tasks: List<TaskEntity>): List<StudyPriorityItem> {
        val currentTime = System.currentTimeMillis()

        return tasks.map { task ->
            val subject = subjects.find { it.id == task.subjectId } ?: SubjectEntity(
                name = "General",
                currentGrade = 70.0,
                colorHex = "#FF5722"
            )

            // 1. Cálculo de Urgencia (basado en días restantes hasta la fecha límite)
            val diffMillis = task.dueDateMillis - currentTime
            val daysLeft = diffMillis / (1000 * 60 * 60 * 24).toDouble()
            val urgencyScore = if (daysLeft <= 0) 100.0 else (10.0 / daysLeft).coerceIn(1.0, 50.0)

            // 2. Cálculo de Gravedad Académica (notas más bajas aumentan la prioridad)
            val academicGravity = (100.0 - subject.currentGrade).coerceIn(10.0, 50.0)

            // Puntaje Total de Prioridad
            val totalScore = urgencyScore + academicGravity

            val reasonText = if (daysLeft <= 1.0) {
                "¡Vence pronto! Urgencia máxima."
            } else {
                "Refuerzo necesario por promedio (${subject.currentGrade.toInt()}%)."
            }

            StudyPriorityItem(
                subjectName = subject.name,
                taskTitle = task.title,
                priorityScore = totalScore,
                reason = reasonText
            )
        }.sortedByDescending { it.priorityScore } // Ordena de mayor a menor prioridad
    }
}