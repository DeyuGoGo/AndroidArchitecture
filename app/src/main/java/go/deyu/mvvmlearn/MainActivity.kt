package go.deyu.mvvmlearn

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import go.deyu.mvvmlearn.data.Note
import go.deyu.mvvmlearn.data.NoteViewModel
import go.deyu.mvvmlearn.main.NoteAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mNoteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mNoteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        val adapter =NoteAdapter()
        recycler_view.adapter = adapter
        mNoteViewModel.allNotes.observe(this, object :Observer<List<Note>>{
            override fun onChanged(t: List<Note>?) {
                if(t==null)
                    return
                adapter.setNotes(t)
                Toast.makeText(this@MainActivity,"onChanged size is ${t.size}",Toast.LENGTH_SHORT).show()
            }
        })
        btn_add.setOnClickListener { mNoteViewModel.insertNote(Note("hello","sss",1)) }
        btn_delete.setOnClickListener { mNoteViewModel.deleteAllNote() }


    }

}