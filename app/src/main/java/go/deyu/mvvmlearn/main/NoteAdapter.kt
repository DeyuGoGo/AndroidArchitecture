package go.deyu.mvvmlearn.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import go.deyu.mvvmlearn.R
import go.deyu.mvvmlearn.data.Note

class NoteAdapter :
    ListAdapter<Note, NoteAdapter.NoteHolder>(diffCallback) {
    private val TAG = "NoteAdapter"

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Note>(){
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id

            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }
        }
    }

    var listener: OnItemClickListener? = null
        set(value) {
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val itemview =
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteHolder(itemview)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val currentNote = getItem(position)
        holder.tv_title.setText(currentNote.title)
        holder.tv_descrition.setText(currentNote.description)
        holder.tv_priority.setText(currentNote.priority.toString())
    }

    fun getNoteAt(index: Int): Note {
        return getItem(index)
    }

    interface OnItemClickListener {
        fun onItemClick(note: Note)
    }

    inner class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_title: TextView
        val tv_descrition: TextView
        val tv_priority: TextView

        init {
            tv_title = itemView.findViewById(R.id.tv_title)
            tv_descrition = itemView.findViewById(R.id.tv_description)
            tv_priority = itemView.findViewById(R.id.tv_priority)
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(getItem(adapterPosition))
                }
            }
        }
    }
}