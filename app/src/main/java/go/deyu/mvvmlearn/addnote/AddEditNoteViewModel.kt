package go.deyu.mvvmlearn.addnote

import android.app.Application
import androidx.lifecycle.*
import go.deyu.mvvmlearn.App
import go.deyu.mvvmlearn.data.Note
import go.deyu.mvvmlearn.data.NotesRepository
import go.deyu.mvvmlearn.data.Result
import kotlinx.coroutines.launch

class AddEditNoteViewModel (application: Application) : AndroidViewModel(application) {
    val repository: NotesRepository
    val note  = MutableLiveData<Note>()
    init {
        repository = (application as App).noteRepository
    }

    fun saveNote(note: Note){
        viewModelScope.launch {
            repository.saveNote(note)
        }
    }

    fun getNote(id:Int) {
        viewModelScope.launch{
            repository.getNote(id).let { result ->
                if(result is Result.Success) {
                    note.value = result.data
                }
            }
        }
    }

}