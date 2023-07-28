package com.example.hw_noteapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hw_noteapp.data.model.NoteModel
import com.example.hw_noteapp.data.source.Database
import com.example.hw_noteapp.databinding.ItemNoteBinding

class NoteAdapter(
    private val onToDoClick: (NoteModel) -> Unit,
    var checkboxClick: (NoteModel) -> Unit = {},
) :RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){

    private val dailyList = mutableListOf<NoteModel>()

    //viewHolder ın creat eldildiği yer benim view a ihtiyacım var
    override fun onCreateViewHolder(parent:ViewGroup,viewType:Int): NoteViewHolder{
        val binding=ItemNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoteViewHolder(binding)


    }

    //notemodel i aldığımız yer
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(dailyList[position])

    }

    inner class NoteViewHolder (private val binding:ItemNoteBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(note:NoteModel){
            with(binding){
                tvTitle.text = note.title
                tvDate.text = note.date
                btnCheckbox.setOnCheckedChangeListener { _, _ ->
                    checkboxClick(note)
                }
                root.setOnClickListener{
                    onToDoClick(note)
                }

            }

        }
    }
    override fun getItemCount(): Int {
        return dailyList.size

    }
    fun updateList(list: List<NoteModel>) {
        dailyList.clear()
        dailyList.addAll(list)
        notifyItemRangeChanged(0, list.size)
    }
    }