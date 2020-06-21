package go.deyu.mvvmlearn

import android.app.Application
import go.deyu.mvvmlearn.data.NotesRepository
import go.deyu.mvvmlearn.data.ServiceLocator

class App :Application() {
    // Depends on the flavor,
    val noteRepository: NotesRepository
        get(): NotesRepository = ServiceLocator.provideNoteRepository(this)

}