package com.studyorganizer.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

// 1. Entidad: Materias
@Entity(tableName = "subjects")
data class SubjectEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val currentGrade: Double, // Para calcular el "Academic Gravity"
    val colorHex: String
)

// 2. Entidad: Tareas y Apuntes
@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val subjectId: Int,
    val title: String,
    val dueDateMillis: Long, // Fecha límite para medir Urgencia
    val isCompleted: Boolean = false
)

// Accesso a Datos (DAO)
@Dao
interface StudyDao {
    @Query("SELECT * FROM subjects")
    suspend fun getAllSubjects(): List<SubjectEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: SubjectEntity)

    @Query("SELECT * FROM tasks WHERE isCompleted = 0 ORDER BY dueDateMillis ASC")
    suspend fun getPendingTasks(): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)
}

// Base de Datos Room
@Database(entities = [SubjectEntity::class, TaskEntity::class], version = 1)
abstract class StudyDatabase : RoomDatabase() {
    abstract fun studyDao(): StudyDao
}