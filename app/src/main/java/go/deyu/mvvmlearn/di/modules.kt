package go.deyu.mvvmlearn.di

import android.app.AlertDialog
import androidx.room.Room
import go.deyu.mvvmlearn.data.note.DefaultNotesRepository
import go.deyu.mvvmlearn.data.note.LocalDataSource
import go.deyu.mvvmlearn.data.note.NoteDatabase
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            NoteDatabase::class.java, "note.db"
        ).build()
    }
    single {
        get<NoteDatabase>().noteDao()
    }
    single {
        LocalDataSource(get(), Dispatchers.IO)
    }
    single {
        DefaultNotesRepository(
            get<LocalDataSource>(),
            Dispatchers.IO
        )
    }
}