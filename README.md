# TaskManager App 📝

Простое и эффективное приложение для управления задачами, созданное в рамках обучения современным практикам Android-разработки. 

## 🚀 Стек технологий
Приложение построено на базе **современного Android-стека**:
* **Язык**: [Kotlin](https://kotlinlang.org/)
* **UI**: [Jetpack Compose](https://developer.android.com/jetpack/compose) (Декларативный интерфейс)
* **Архитектура**: MVVM (Model-View-ViewModel)
* **База данных**: [Room Persistence Library](https://developer.android.com/training/data-storage/room) (SQLite)
* **DI (опционально)**: Инъекция зависимостей через ручной DI / ViewModelFactory
* **Асинхронность**: [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)

## ✨ Основные возможности
- [x] Создание, редактирование и удаление задач.
- [x] Локальное хранение данных (задачи не пропадают после перезагрузки).
- [x] Отметка о выполнении задачи в одно касание.
- [x] Темная и светлая темы оформления (Material 3).

## 🛠 Архитектура
Проект следует принципам **Clean Architecture** на базовом уровне:
1.  **Data Layer**: Room Database, DAO и репозиторий.
2.  **Domain Layer**: Модели данных (`Task`).
3.  **UI Layer**: Composable-функции и ViewModel, управляющая состоянием экрана (State UI).



## 📦 Как запустить
1. Склонируйте репозиторий:
   ```bash
   git clone [https://github.com/ваш_логин/task-manager-android.git](https://github.com/ваш_логин/task-manager-android.git)

   Откройте проект в Android Studio (версия Hedgehog или новее).

Дождитесь завершения синхронизации Gradle.

Запустите на эмуляторе с API 24+.


👨‍💻 Автор
Арсен Студент 4 курса, специализация: Mobile Development.

Стремлюсь к позиции Junior Android Developer.
