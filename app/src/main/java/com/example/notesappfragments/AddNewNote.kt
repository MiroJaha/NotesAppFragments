package com.example.notesappfragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import io.github.muddz.styleabletoast.StyleableToast

class AddNewNote: Fragment() {

    private val myViewModel by lazy { ViewModelProvider(this).get(MyViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_update_note, container, false)
        val pk= arguments?.getInt("pk")
        var note= arguments?.getString("note")

        Log.d("MyData","$pk $note")

        val showedMessage= view.findViewById<TextView>(R.id.oldMessageTV)
        showedMessage.text= note
        val entry= view.findViewById<EditText>(R.id.newMessageEntry)

        view.findViewById<Button>(R.id.updateButton).setOnClickListener{
            if (entry.text.isNotBlank()){
                myViewModel.updateNote(Data(pk!!,entry.text.toString()))
                showedMessage.text= entry.text.toString()
                entry.text.clear()
                entry.clearFocus()
            }
            else
                StyleableToast.makeText(requireContext(),"Please Enter New Note!!",R.style.myToast).show()
        }
        view.findViewById<Button>(R.id.cancelButton).setOnClickListener{
            Navigation.findNavController(requireView()).navigate(R.id.action_addNewNote_to_showNotes2)
        }

        return view
    }

}