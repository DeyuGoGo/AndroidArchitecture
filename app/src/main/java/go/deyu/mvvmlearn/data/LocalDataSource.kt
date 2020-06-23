package go.deyu.mvvmlearn.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class LocalDataSource internal constructor(
    private val noteDao: NoteDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : NoteDataSource {
    private val db :NoteDatabase? = null
    override fun observeNotes(): LiveData<Result<List<Note>>> {
        return noteDao.observeNotes().map {
            Result.Success(it)
        }
    }

    override suspend fun getNotes(): Result<List<Note>> =
        withContext(ioDispatcher) {
            try {
                Result.Success(noteDao.getAllNotes())
            } catch (e: Exception) {
                Result.Error(e)
            }
        }


    override suspend fun refreshNotes() {
        TODO("Not yet implemented")
    }

    override suspend fun getNote(NoteId: Int): Result<Note> =
        withContext(ioDispatcher) {
            try {
                val noteById = noteDao.getNoteById(NoteId)
                if(noteById == null){
                    throw Exception("Not found this id=${NoteId} note ")
                }
                Result.Success(noteById)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }


    override suspend fun saveNote(Note: Note) {
        withContext(ioDispatcher) {
            noteDao.upsert(note = Note)
        }
    }

    override suspend fun deleteAllNotes() {
        withContext(ioDispatcher) {
            noteDao.deleteAllNotes()
        }
    }

    override suspend fun deleteNote(NoteId: Int) {
        withContext(ioDispatcher) {
            val noteById = noteDao.getNoteById(NoteId)
            if (noteById != null)
                noteDao.delete(noteById)
        }
    }

}