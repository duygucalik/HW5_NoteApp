package com.example.hw_noteapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteModel(
    val id:Int,
    val title: String,
    val desc: String?,
    val date :String,
    var isDone: Boolean =true

): Parcelable
