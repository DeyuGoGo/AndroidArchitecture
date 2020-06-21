package go.deyu.mvvmlearn.data

import androidx.lifecycle.LiveData
import androidx.room.*
import go.deyu.mvvmlearn.data.Note

@Dao
interface NoteDao {
    @Insert
    fun insert(note: Note)
    @Update
    fun update(noet: Note)
    @Delete
    fun delete(note: Note)

    @Query("DELETE FROM note_table")
    fun deleteAllNotes()

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    fun observeNotes():LiveData<List<Note>>

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    fun getAllNotes():List<Note>
}