package go.deyu.mvvmlearn.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import go.deyu.mvvmlearn.R
import go.deyu.mvvmlearn.data.Note

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    private val TAG = "NoteAdapter"
    private var notes :List<Note> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return NoteHolder(itemview)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val currentNote = notes.get(position)
        holder.tv_title.setText(currentNote.title)
        holder.tv_descrition.setText(currentNote.description)
        holder.tv_priority.setText(""+currentNote.priority)

    }
    fun setNotes(notes:List<Note>){
        Log.d(TAG, "setNotes:${notes.size} ")
        this.notes = notes
        notifyDataSetChanged()
    }
    class NoteHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val tv_title:TextView
        val tv_descrition:TextView
        val tv_priority:TextView
        init{
            tv_title = itemView.findViewById(R.id.tv_title)
            tv_descrition = itemView.findViewById(R.id.tv_description)
            tv_priority = itemView.findViewById(R.id.tv_priority)
        }

    }
}