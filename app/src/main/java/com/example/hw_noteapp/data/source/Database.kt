package com.example.hw_noteapp.data.source

import com.example.hw_noteapp.data.model.NoteModel

object Database {

   // private val dailyNotes = mutableListOf<NoteModel>()

    private val dailyList = mutableListOf<NoteModel>()
    private val complateList = mutableListOf<NoteModel>()

    fun getDailyNotes() = dailyList
    fun getComplateNotes() = complateList


    fun addDailyNotes(title: String, desc: String, date: String, isDone: Boolean){
        dailyList.add(
            NoteModel(
                id = (dailyList.lastOrNull()?.id ?: 0) + 1,
                title = title,
                desc = desc,
                date = date,
                isDone = isDone
            )
        )
    }

    fun removeDailyNote(note:NoteModel)=dailyList.remove(note)
    fun removeNoteInComplated(note: NoteModel) = complateList.remove(note)

    fun updateNoteList(index: Int, note: NoteModel) {
        dailyList[index] = note
    }

    fun addNoteInComplated(title: String, desc: String, date: String, isDone: Boolean){
        complateList.add(
            NoteModel(
                id = (dailyList.lastOrNull()?.id ?: 0) + 1,
                title = title,
                desc = desc,
                date = date,
                isDone = isDone
            )
        )
    }


}