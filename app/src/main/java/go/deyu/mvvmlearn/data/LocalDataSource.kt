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

    override suspend fun getNote(NoteId: String): Result<Note> {
        TODO("Not yet implemented")
    }

    override suspend fun saveNote(Note: Note) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllNotes() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNote(NoteId: String) {
        TODO("Not yet implemented")
    }

}