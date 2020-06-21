package go.deyu.mvvmlearn.data

import androidx.lifecycle.LiveData

interface NoteDataSource {

    fun observeNotes(): LiveData<Result<List<Note>>>

    suspend fun getNotes(): Result<List<Note>>

    suspend fun refreshNotes()

    suspend fun getNote(NoteId: String): Result<Note>

    suspend fun saveNote(Note: Note)

    suspend fun deleteAllNotes()

    suspend fun deleteNote(NoteId: String)
}