package go.deyu.mvvmlearn.data

import androidx.lifecycle.LiveData
import go.deyu.mvvmlearn.data.NoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DefaultNotesRepository(
    private val noteDataSource: NoteDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : NotesRepository {
    private var allNotes: LiveData<Result<List<Note>>>
    init {
        allNotes = noteDataSource.observeNotes()
    }
    override fun observeNotes(): LiveData<Result<List<Note>>> {
        return noteDataSource.observeNotes()
    }

    override suspend fun getNotes(forceUpdate: Boolean): Result<List<Note>> {
        return noteDataSource.getNotes()
    }

    override suspend fun refreshNotes() {
        noteDataSource.refreshNotes()
    }

    override suspend fun getNote(NoteId: String, forceUpdate: Boolean): Result<Note> {
        return noteDataSource.getNote(NoteId)
    }

    override suspend fun saveNote(Note: Note) {
        noteDataSource.saveNote(Note)
    }

    override suspend fun deleteAllNotes() {
        noteDataSource.deleteAllNotes()
    }

    override suspend fun deleteNote(NoteId: String) {
        noteDataSource.deleteNote(NoteId)
    }

}
