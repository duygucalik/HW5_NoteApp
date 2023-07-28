package com.example.hw_noteapp.ui.notes

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hw_noteapp.R
import com.example.hw_noteapp.data.model.NoteModel
import com.example.hw_noteapp.data.model.showDatePicker
import com.example.hw_noteapp.data.model.showTimePicker
import com.example.hw_noteapp.data.source.Database
import com.example.hw_noteapp.databinding.AddAlertDialogBinding
import com.example.hw_noteapp.databinding.FragmentDailyNoteBinding
import com.example.hw_noteapp.ui.adapter.NoteAdapter
import java.util.Calendar

class DailyNoteFragment : Fragment(R.layout.fragment_daily_note) {

    private lateinit var binding: FragmentDailyNoteBinding

    private val dailyNotesAdapter =NoteAdapter(::onNoteClick)


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentDailyNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            setData(Database.getDailyNotes())

            btnFloating.setOnClickListener {
                showAddDialog()
            }
            checkBoxClick()
        }
    }
    private fun setData(list: List<NoteModel>) {
        binding.rvDailyNote.adapter = dailyNotesAdapter
        dailyNotesAdapter.updateList(list)
    }

    private fun showAddDialog(){

        val builder = AlertDialog.Builder(requireContext())

        val binding = AddAlertDialogBinding.inflate(layoutInflater) //tasarım tanıttık
        builder.setView(binding.root)

        val dialog=builder.create()

        var selectedDate = ""

        with(binding){
            val calendar = Calendar.getInstance()

            switchDate.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    showDatePicker(calendar) { year, month, day ->
                        showTimePicker(calendar) { hour, minute ->
                            selectedDate = "$day.$month.$year - $hour:$minute"
                            tvDate.text = "$day.$month.$year - $hour:$minute"
                            tvDate.visibility = View.VISIBLE
                        }
                    }
                }
            }

            btnAdd.setOnClickListener{
                val title= etTitle.text.toString()
                val desc=etDesc.text.toString()

                if (title.isNotEmpty() && desc.isNotEmpty()) {
                    Database.addDailyNotes(title, desc, selectedDate, false) //database e ekleme yaptığımız kısım
                    setData(Database.getDailyNotes())// database i güncellediğimiz kısım
                    dialog.dismiss()
                }
            }
            btnCancel.setOnClickListener{
                dialog.dismiss()

            }
        }
        dialog.show()
    }

    private fun onNoteClick(notes: NoteModel) {
        val action = DailyNoteFragmentDirections.actionDailyNoteFragmentToDetailFragment(notes)
        findNavController().navigate(action)
    }

    private fun checkBoxClick() {
        dailyNotesAdapter.checkboxClick = { note ->
            Database.removeDailyNote(note)
            note.desc?.let {
                Database.addNoteInComplated(
                    note.title,
                    it,
                    note.date,
                    note.isDone
                )
            }
            val getList = Database.getDailyNotes()
            binding.rvDailyNote.adapter = dailyNotesAdapter
            dailyNotesAdapter.updateList(getList)
        }
    }
}