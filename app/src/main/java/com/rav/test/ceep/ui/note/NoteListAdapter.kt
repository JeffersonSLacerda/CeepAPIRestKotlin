package com.rav.test.ceep.ui.note

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rav.test.ceep.R
import com.rav.test.ceep.data.model.Note
import kotlinx.android.synthetic.main.note_item.view.*

class NoteListAdapter(private val notes: ArrayList<Note>,
                      private var context: Context): RecyclerView.Adapter<NoteListAdapter. ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val note = notes[position]
        holder.let{
            it.title.text = note.title
            it.description.text = note.description
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val title = itemView.note_item_title
        val description = itemView.note_item_description
    }
}