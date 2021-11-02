package com.example.notesappfragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.annotation.Nullable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.muddz.styleabletoast.StyleableToast


class ShowNotes : Fragment() {

    private val myViewModel by lazy { ViewModelProvider(this).get(MyViewModel::class.java) }

    private lateinit var mainRV: RecyclerView
    private lateinit var noteEntry: EditText
    private lateinit var adapter: RVAdapter
    private lateinit var notes: ArrayList<Data>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_show_notes, container, false)

        mainRV= view.findViewById(R.id.rvMain)
        noteEntry= view.findViewById(R.id.noteEntry)

        notes= arrayListOf()
        adapter= RVAdapter(this,notes)
        mainRV.adapter= adapter
        mainRV.layoutManager= LinearLayoutManager(context)

        myViewModel.gettingNotes().observe(viewLifecycleOwner){
                notesList ->
            notes.clear()
            notes.addAll(notesList)
            adapter.notifyDataSetChanged()
        }

        view.findViewById<Button>(R.id.saveButton).setOnClickListener{
            if (noteEntry.text.isNotBlank()){
                myViewModel.addNote(Data(0,noteEntry.text.toString()))
                StyleableToast.makeText(requireContext(), "Saved Successfully!!", R.style.myToast).show()
                noteEntry.text.clear()
                noteEntry.clearFocus()
            }
            else
                StyleableToast.makeText(requireContext(),"Please Enter Valid Values!!",R.style.myToast).show()
        }


        return view
    }


    fun update(note: Data) {
        val bundle= Bundle()
        bundle.putInt("pk",note.pk)
        bundle.putString("note",note.note)
        Navigation.findNavController(requireView()).navigate(R.id.action_showNotes_to_addNewNote,bundle)
    }

    fun delete(note: Data) {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("Yes") {
                    _,_ ->
                myViewModel.deleteNote(note)
            }
            .setNegativeButton("Cancel") {dialog,_ -> dialog.cancel()
            }
        val alert = dialogBuilder.create()
        alert.setTitle("Are You Sure You Want to Delete Note")
        alert.show()
    }

}