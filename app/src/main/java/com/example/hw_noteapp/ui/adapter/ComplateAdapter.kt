package com.example.hw_noteapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.createSavedStateHandle
import androidx.recyclerview.widget.RecyclerView
import com.example.hw_noteapp.data.model.NoteModel
import com.example.hw_noteapp.databinding.ItemNoteBinding

class ComplateAdapter(private val savedonToDoClick: (NoteModel) -> Unit,
                      var savedcheckboxClick: (NoteModel) -> Unit = {},
) :
    RecyclerView.Adapter<ComplateAdapter.NoteViewHolder>(){

        private val complateList = mutableListOf<NoteModel>()

        //viewHolder ın creat eldildiği yer benim view a ihtiyacım var
        override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): NoteViewHolder{
            val binding= ItemNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return NoteViewHolder(binding)

        }
        //notemodel i aldığımız yer
        override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
            holder.bind(complateList[position])

        }

        inner class NoteViewHolder (private val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root){
            fun bind(note: NoteModel){
                with(binding){
                    tvTitle.text = note.title
                    tvDate.text = note.date
                    btnCheckbox.isChecked=true
                    btnCheckbox.setOnCheckedChangeListener { _, _ ->
                        savedcheckboxClick(note)
                    }
                    root.setOnClickListener{
                        savedonToDoClick(note)
                    }

                }

            }
        }
        override fun getItemCount(): Int {
            return complateList.size

        }
        fun updateList(list: List<NoteModel>) {
            complateList.clear()
            complateList.addAll(list)
            notifyItemRangeChanged(0, list.size)
        }
    }
