package go.deyu.mvvmlearn

import android.content.Intent
import android.nfc.NfcAdapter.EXTRA_ID
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import go.deyu.mvvmlearn.addnote.AddEditNoteActivity
import go.deyu.mvvmlearn.data.Note
import go.deyu.mvvmlearn.data.NoteViewModel
import go.deyu.mvvmlearn.main.NoteAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val mNoteViewModel: NoteViewModel by viewModels()
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        val adapter =NoteAdapter()
        adapter.listener = object : NoteAdapter.OnItemClickListener{
            override fun onItemClick(note: Note) {
                Log.d(TAG, "onItemClick")
                val intent = Intent(this@MainActivity,AddEditNoteActivity::class.java)
                intent.putExtra(AddEditNoteActivity.EXTRA_ID,note.id)
                startActivity(intent)
            }
        }
        recycler_view.adapter = adapter
        mNoteViewModel.allNotes.observe(this, object :Observer<List<Note>>{
            override fun onChanged(t: List<Note>?) {
                if(t==null)
                    return
                adapter.setNotes(t)
                Toast.makeText(this@MainActivity,"onChanged size is ${t.size}",Toast.LENGTH_SHORT).show()
            }
        })
        btn_add.setOnClickListener { startActivity(Intent(this,AddEditNoteActivity::class.java)) }
        btn_delete.setOnClickListener { mNoteViewModel.deleteAllNote() }
        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapterPosition = viewHolder.adapterPosition
                val noteAt = adapter.getNoteAt(adapterPosition)
                mNoteViewModel.deleteNote(noteAt)
            }
        }).attachToRecyclerView(recycler_view)
    }

}