package com.studyorganizer.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.studyorganizer.di.AppModule

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Obtenemos nuestro ViewModel usando el módulo de inyección de dependencias
        val viewModel = AppModule.provideDashboardViewModel(applicationContext)

        setContent {
            MaterialTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Llamamos a nuestra pantalla principal
                    DashboardScreen(
                        onUploadClick = {
                            // Aquí programaremos la subida de archivos o llamada a la IA próximamente
                        },
                        onEditTempScheduleClick = {
                            // Aquí abriremos el diálogo para ajustar el horario del día
                        }
                    )
                }
            }
        }
    }
}