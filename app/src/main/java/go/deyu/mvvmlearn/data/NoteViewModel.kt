package go.deyu.mvvmlearn.data

import android.app.Application
import androidx.lifecycle.*
import go.deyu.mvvmlearn.App
import kotlinx.coroutines.launch
import kotlin.Result

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    val repository: NotesRepository
    val allNotes: LiveData<List<Note>>;

    init {
        repository = (application as App).noteRepository
        allNotes = repository.observeNotes().switchMap { filterNotes(it) }
    }

    fun insertNote(note: Note){
        viewModelScope.launch {
            repository.saveNote(note)
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