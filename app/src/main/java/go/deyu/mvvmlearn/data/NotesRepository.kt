package go.deyu.mvvmlearn.data

import androidx.lifecycle.LiveData

interface NotesRepository {

    fun observeNotes(): LiveData<Result<List<Note>>>

    suspend fun getNotes(forceUpdate: Boolean = false): Result<List<Note>>

    suspend fun refreshNotes()

    suspend fun getNote(NoteId: String, forceUpdate: Boolean = false): Result<Note>

    suspend fun saveNote(Note: Note)

    suspend fun deleteAllNotes()

    suspend fun deleteNote(NoteId: String)
}
