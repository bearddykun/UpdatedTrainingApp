package com.example.updatedtrainingapp.fragments.trainingsChoice

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.application.MySharedPreferences
import com.example.updatedtrainingapp.dataBase.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateTrainingFragment :
    DialogFragment(), TextView.OnEditorActionListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_training, container, false)
        view.findViewById<EditText>(R.id.trainingFragmentText).setOnEditorActionListener(this)
        return view
    }

    override fun onEditorAction(textView: TextView?, i: Int, keyEvent: KeyEvent?): Boolean {
        if (i == EditorInfo.IME_ACTION_DONE) {
            val list = MySharedPreferences.getList(Constants.SAVE_NEW_TRAINING_LIST)
            list.also {
                it.add(textView?.text.toString())
                MySharedPreferences.saveList(Constants.SAVE_NEW_TRAINING_LIST, it)
            }
            hideKeyboard()
            findNavController().navigate(CreateTrainingFragmentDirections.actionFragmentCreateTrainingToFragmentThisTrainingFragment())
            return true
        }
        return false
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager =
            activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

        var view = activity?.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
