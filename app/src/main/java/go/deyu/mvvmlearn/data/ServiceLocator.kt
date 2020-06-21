package go.deyu.mvvmlearn.data

import android.content.Context
import androidx.room.Room

object ServiceLocator {
    private var database: NoteDatabase? =null
    @Volatile
    var tasksRepository: DefaultNotesRepository? = null

    fun provideNoteRepository(context: Context): DefaultNotesRepository {
        synchronized(this) {
            return tasksRepository
                ?: tasksRepository
                ?: createTasksRepository(
                    context
                )
        }
    }
    private fun createTasksRepository(context: Context): DefaultNotesRepository {
        val newRepo = DefaultNotesRepository(
            createTaskLocalDataSource(
                context
            )
        )
        tasksRepository = newRepo
        return newRepo
    }
    private fun createTaskLocalDataSource(context: Context): NoteDataSource {
        val database = database
            ?: createDataBase(context)
        return LocalDataSource(database.noteDao())
    }

    private fun createDataBase(context: Context): NoteDatabase {
        val result = Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java, "note.db"
        ).build()
        database = result
        return result
    }
}