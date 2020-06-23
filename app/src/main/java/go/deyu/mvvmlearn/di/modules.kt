package go.deyu.mvvmlearn.di

import androidx.room.Room
import go.deyu.mvvmlearn.data.DefaultNotesRepository
import go.deyu.mvvmlearn.data.LocalDataSource
import go.deyu.mvvmlearn.data.NoteDatabase
import go.deyu.mvvmlearn.main.NoteViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val applicationModule = module {
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
        DefaultNotesRepository(get<LocalDataSource>(), Dispatchers.IO)
    }
}