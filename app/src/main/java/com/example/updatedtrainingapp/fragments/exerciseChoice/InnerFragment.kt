package com.example.updatedtrainingapp.fragments.exerciseChoice

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.dataBase.objects.ExerciseObject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_exercise_choice.*
import org.jetbrains.anko.backgroundColor
import java.util.*

private const val ARG_PARAM1 = "param1"

@AndroidEntryPoint
class InnerFragment : Fragment(), ExercisesChoiceAdapter.OnExerciseChoiceItemListener {

    private var list: List<ExerciseObject>? = null
    private val viewModel: ExerciseChoiceViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list = arguments?.getParcelableArrayList(ARG_PARAM1)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_exercise_choice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ExercisesChoiceAdapter()
        adapter.setOnExerciseChoiceItemListener(this)
        list?.let { adapter.swapAdapter(it) }
        exerciseChoiceRecyclerView.adapter = adapter
    }

    companion object {
        fun newInstance(list: List<ExerciseObject>): InnerFragment {
            val mainFragment = InnerFragment()
            val bundle = Bundle()

            bundle.putParcelableArrayList(ARG_PARAM1, list as ArrayList)
            mainFragment.arguments = bundle

            return mainFragment
        }
    }

    override fun onExerciseChoiceItemClick(exercise: String, view: View) {
        addRemoveExercise(exercise, view)
    }

    private fun addRemoveExercise(exercise: String, view: View) {
        if (viewModel.listOfNewExercises.contains(exercise)) {
            viewModel.listOfNewExercises.remove(exercise)
            view.backgroundColor = Color.WHITE
            return
        }
        viewModel.listOfNewExercises.add(exercise)
        view.backgroundColor = Color.BLUE
    }
}