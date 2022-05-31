package com.nhlynn.hilt_room_mvvm.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nhlynn.hilt_room_mvvm.databinding.NoteItemBinding
import com.nhlynn.hilt_room_mvvm.db.NoteEntity
import com.nhlynn.hilt_room_mvvm.delegate.NoteDelegate
import com.nhlynn.hilt_room_mvvm.utils.MyUtil

class NoteAdapter(delegate: NoteDelegate) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mDelegate = delegate
    private var noteList = ArrayList<NoteEntity>()

    class NoteViewHolder(val viewBinder: NoteItemBinding) : RecyclerView.ViewHolder(viewBinder.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as NoteViewHolder
        val root = viewHolder.viewBinder

        val item = noteList[position]

        root.cvNote.setOnClickListener {
            mDelegate.onViewNote(item)
        }

        root.tvNote.text = item.description
        root.tvDate.text = MyUtil.convertDate(item.updatedAt)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(noteList: List<NoteEntity>) {
        this.noteList =
            noteList as ArrayList<NoteEntity> /* = java.util.ArrayList<com.nhlynn.hilt_room_mvvm.db.NoteEntity> */
        notifyDataSetChanged()
    }
}