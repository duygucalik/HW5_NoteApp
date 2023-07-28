package layout

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hw_noteapp.R
import com.example.hw_noteapp.common.viewBinding
import com.example.hw_noteapp.data.model.NoteModel
import com.example.hw_noteapp.data.source.Database
import com.example.hw_noteapp.databinding.FragmentComplatedBinding
import com.example.hw_noteapp.ui.adapter.ComplateAdapter
import com.example.hw_noteapp.ui.adapter.NoteAdapter


class ComplatedFragment : Fragment(R.layout.fragment_complated) {

    private lateinit var binding: FragmentComplatedBinding

    private val dailyNotesAdapter = ComplateAdapter(::onNoteClick)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val getList = Database.getComplateNotes()
        binding.rvComplateNote.adapter = dailyNotesAdapter
        dailyNotesAdapter.updateList(getList)
        checkBoxClick()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentComplatedBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun onNoteClick(notes: NoteModel) {
        val action = ComplatedFragmentDirections.actionComplatedFragmentToDetailFragment(notes)
        findNavController().navigate(action)
    }

    private fun checkBoxClick() {
        dailyNotesAdapter.savedcheckboxClick = { note ->
            Database.removeNoteInComplated(note)
            note.desc?.let {
                Database.addDailyNotes(
                    note.title,
                    it,
                    note.date,
                    note.isDone
                )
            }
            val getList = Database.getComplateNotes()
            binding.rvComplateNote.adapter = dailyNotesAdapter
            dailyNotesAdapter.updateList(getList)

        }
    }
}