package com.studyorganizer.di

import android.content.Context
import androidx.room.Room
import com.studyorganizer.data.StudyDatabase
import com.studyorganizer.domain.CalculatePriorityUseCase
import com.studyorganizer.ui.DashboardViewModel

object AppModule {
    
    private var database: StudyDatabase? = null

    // Inicializador global de la Base de Datos Room
    fun provideDatabase(context: Context): StudyDatabase {
        return database ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                StudyDatabase::class.java,
                "study_database"
            ).build()
            database = instance
            instance
        }
    }

    // Proveedor del ViewModel principal
    fun provideDashboardViewModel(context: Context): DashboardViewModel {
        val db = provideDatabase(context)
        return DashboardViewModel(
            dao = db.studyDao(),
            calculatePriorityUseCase = CalculatePriorityUseCase()
        )
    }
}