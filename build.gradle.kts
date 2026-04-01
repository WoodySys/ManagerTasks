plugins {
    // Android Gradle Plugin 8.2.0 — стабильный и проверенный
    id("com.android.application") version "8.2.0" apply false
    id("com.android.library") version "8.2.0" apply false

    // Kotlin 1.9.20 — в нем уже исправлена ошибка с HasConvention
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false

    // KSP для работы базы данных Room
    id("com.google.devtools.ksp") version "1.9.20-1.0.14" apply false
}