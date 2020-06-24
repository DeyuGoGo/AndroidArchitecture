package go.deyu.mvvmlearn.data.note

import androidx.lifecycle.LiveData
import go.deyu.mvvmlearn.data.Result

interface NotesRepository {

    fun observeNotes(): LiveData<Result<List<Note>>>

    suspend fun getNotes(forceUpdate: Boolean = false): Result<List<Note>>

    suspend fun refreshNotes()

    suspend fun getNote(NoteId: Int, forceUpdate: Boolean = false): Result<Note>

    suspend fun saveNote(Note: Note)

    suspend fun deleteAllNotes()

    suspend fun deleteNote(NoteId: Int)
}
