package com.example.managertasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

// 1. Модель данных
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val isCompleted: Boolean = false
)

// 2. Интерфейс базы данных
@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY id DESC")
    fun getAllTasks(): Flow<List<Task>>
    @Insert
    suspend fun insertTask(task: Task)
    @Update
    suspend fun updateTask(task: Task)
    @Delete
    suspend fun deleteTask(task: Task)
}

// 3. Сама база данных
@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}

// 4. Логика и Экран
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализация базы данных
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "my_tasks_db"
        ).build()
        val dao = db.taskDao()

        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TaskManagerScreen(dao)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskManagerScreen(dao: TaskDao) {
    val tasks by dao.getAllTasks().collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("FocusFlow", style = MaterialTheme.typography.headlineLarge)

        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Новая задача") },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    if (text.isNotBlank()) {
                        scope.launch { dao.insertTask(Task(title = text)); text = "" }
                    }
                },
                modifier = Modifier.padding(start = 8.dp).height(56.dp)
            ) { Text("ОК") }
        }

        LazyColumn {
            items(tasks) { task ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
                        Checkbox(
                            checked = task.isCompleted,
                            onCheckedChange = { scope.launch { dao.updateTask(task.copy(isCompleted = it)) } }
                        )
                        Text(
                            text = task.title,
                            modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                            textDecoration = if (task.isCompleted) TextDecoration.LineThrough else null
                        )
                        IconButton(onClick = { scope.launch { dao.deleteTask(task) } }) {
                            Icon(Icons.Default.Delete, contentDescription = null, tint = MaterialTheme.colorScheme.error)
                        }
                    }
                }
            }
        }
    }
}