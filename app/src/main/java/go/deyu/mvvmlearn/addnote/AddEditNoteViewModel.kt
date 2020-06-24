package go.deyu.mvvmlearn.addnote

import android.app.Application
import androidx.lifecycle.*
import go.deyu.mvvmlearn.data.note.DefaultNotesRepository
import go.deyu.mvvmlearn.data.note.Note
import go.deyu.mvvmlearn.data.Result
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class AddEditNoteViewModel (application: Application) : AndroidViewModel(application) ,KoinComponent{
    val note  = MutableLiveData<Note>()
    val repository: DefaultNotesRepository by inject()
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