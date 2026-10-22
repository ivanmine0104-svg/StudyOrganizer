# StudyOrganizer - Project Architecture

## Clean Architecture & MVVM Pattern
This project implements modern Android development standards using Kotlin and Jetpack Compose, structured for modularity and scalability.

### Module Hierarchy:
* **`data/`**: Data layer containing local storage schemas via **Room Database** (`StudyDatabase`, entities for subjects and tasks) to ensure offline-first capabilities.
* **`domain/`**: Business logic layer housing use cases (`CalculatePriorityUseCase`), applying weighted algorithms for task urgency and academic gravity.
* **`ui/`**: Presentation layer featuring reactive UI components built with **Jetpack Compose** (`DashboardScreen`) managed through **ViewModels**.
* **`di/`**: Reserved module for dependency injection configuration.

---
*Developed for efficient academic time management and smart prioritization.*