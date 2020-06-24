package go.deyu.mvvmlearn.addnote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import go.deyu.mvvmlearn.R
import go.deyu.mvvmlearn.data.note.Note
import kotlinx.android.synthetic.main.activity_add_note.*

class AddEditNoteActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "id"
    }

    var id: Int = 0
    val viewModel: AddEditNoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        np_priority.minValue = 1
        np_priority.maxValue = 10
        viewModel.note.observe(this, object : Observer<Note> {
            override fun onChanged(t: Note?) {
                if (t != null) {
                    et_title.setText(t.title)
                    et_description.setText(t.description)
                    np_priority.value = t.priority
                }
            }
        })
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note")
            id = intent.getIntExtra(EXTRA_ID, 0)
            viewModel.getNote(id)
        } else {
            setTitle("Add note")
        }
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_add)
        setTitle("Add Note")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_note -> {
                saveNote()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveNote() {
        val title = et_title.text.toString().trim()
        val description = et_description.text.toString().trim()
        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "幹你是不會輸入資料？", Toast.LENGTH_SHORT).show()
            return
        }
        val note = Note(
            title,
            description,
            np_priority.value
        )
        note.id = id
        viewModel.saveNote(note)
        finish()
    }
}