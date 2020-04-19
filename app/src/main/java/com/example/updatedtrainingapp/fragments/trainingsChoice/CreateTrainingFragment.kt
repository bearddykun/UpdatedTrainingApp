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
import com.example.updatedtrainingapp.application.ThisApplication
import org.jetbrains.anko.find
import javax.inject.Inject


class CreateTrainingFragment :
    DialogFragment(), TextView.OnEditorActionListener {

    @Inject lateinit var trainingChoiceFragment: TrainingsChoiceFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_training, container, false)
        view.find<EditText>(R.id.trainingFragmentText).setOnEditorActionListener(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.application as ThisApplication).appComponent.inject(this)
    }


    override fun onEditorAction(textView: TextView?, i: Int, keyEvent: KeyEvent?): Boolean {
        if (i == EditorInfo.IME_ACTION_DONE) {
            trainingChoiceFragment.updateList(textView?.text.toString())
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
