package go.deyu.mvvmlearn.main

import android.app.Application
import androidx.lifecycle.*
import go.deyu.mvvmlearn.App
import go.deyu.mvvmlearn.data.DefaultNotesRepository
import go.deyu.mvvmlearn.data.Note
import go.deyu.mvvmlearn.data.NotesRepository
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class NoteViewModel(application: Application) : AndroidViewModel(application) , KoinComponent{
    val repository: DefaultNotesRepository by inject()
    val allNotes: LiveData<List<Note>>;

    init {
        allNotes = repository.observeNotes().switchMap { filterNotes(it) }
    }

    fun insertNote(note: Note){
        viewModelScope.launch {
            repository.saveNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch{
            repository.deleteNote(note.id)
        }
    }

    fun deleteAllNote(){
        viewModelScope.launch {
            repository.deleteAllNotes()
        }
    }

    private fun filterNotes(tasksResult: go.deyu.mvvmlearn.data.Result<List<Note>>): LiveData<List<Note>> {
        val result = MutableLiveData<List<Note>>()
        if (tasksResult is go.deyu.mvvmlearn.data.Result.Success) {
            viewModelScope.launch {
                result.value = filterItems(tasksResult.data)
            }
        } else {
            result.value = emptyList()
        }
        return result
    }

    private fun filterItems(tasks: List<Note>): List<Note> {
        val tasksToShow = ArrayList<Note>()
        // We filter the tasks based on the requestType
        for (task in tasks) {
            tasksToShow.add(task)
        }
        return tasksToShow
    }

}