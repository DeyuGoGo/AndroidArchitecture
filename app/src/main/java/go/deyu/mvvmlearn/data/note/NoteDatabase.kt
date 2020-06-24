package go.deyu.mvvmlearn.data.note

import androidx.room.Database
import androidx.room.RoomDatabase
import go.deyu.mvvmlearn.data.note.Note
import go.deyu.mvvmlearn.data.note.NoteDao

@Database(entities = [Note::class],version = 1)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun noteDao(): NoteDao
}