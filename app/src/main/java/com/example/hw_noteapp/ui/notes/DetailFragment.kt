package com.example.hw_noteapp.ui.notes

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hw_noteapp.R
import com.example.hw_noteapp.common.viewBinding
import com.example.hw_noteapp.data.source.Database
import com.example.hw_noteapp.databinding.FragmentComplatedBinding
import com.example.hw_noteapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private lateinit var binding: FragmentDetailBinding

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            tvBaslK.text = args.note.title
            tvDescription.text = args.note.desc
            tvDatetime.text = args.note.date

            btnDelete.setOnClickListener {
                AlertDialog.Builder(requireContext())
                    .setTitle("Delete This Note !!")
                    .setMessage("Are you sure you want to delete this task?")
                    .setPositiveButton("Delete") {_,_ ->
                        if(args.note.isDone) {
                            Database.removeNoteInComplated(args.note)
                        } else {
                            Database.removeDailyNote(args.note)
                        }
                    }.setNegativeButton("Cancel", null).show()

                }

            }
   /*         binding.btnDelete.setOnClickListener() {
            val action = DetailFragmentDirections.actionDetailNoteScreenToNotesScreen()
            findNavController().navigate(action)*/
        }

        }
