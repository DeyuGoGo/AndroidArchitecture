package go.deyu.mvvmlearn.data.note

import androidx.lifecycle.LiveData
import go.deyu.mvvmlearn.data.Result

interface NoteDataSource {

    fun observeNotes(): LiveData<Result<List<Note>>>

    suspend fun getNotes(): Result<List<Note>>

    suspend fun refreshNotes()

    suspend fun getNote(NoteId: Int): Result<Note>

    suspend fun saveNote(Note: Note)

    suspend fun deleteAllNotes()

    suspend fun deleteNote(NoteId: Int)
}