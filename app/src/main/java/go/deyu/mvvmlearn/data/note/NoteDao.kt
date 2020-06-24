package go.deyu.mvvmlearn.data.note

import androidx.lifecycle.LiveData
import androidx.room.*
import go.deyu.mvvmlearn.data.note.Note


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note):Long
    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(noet: Note)
    @Delete
    fun delete(note: Note)

    fun upsert(note: Note) {
        val id: Long = insert(note)
        if (id == -1L) {
            update(note)
        }
    }

    @Query("DELETE FROM note_table")
    fun deleteAllNotes()

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    fun observeNotes():LiveData<List<Note>>

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    fun getAllNotes():List<Note>

    @Query("SELECT * FROM note_table WHERE id = :id")
    fun getNoteById(id: Int): Note?
}