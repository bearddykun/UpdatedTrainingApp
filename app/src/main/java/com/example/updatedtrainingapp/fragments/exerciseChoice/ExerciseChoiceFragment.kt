package com.example.updatedtrainingapp.fragments.exerciseChoice

import android.graphics.Color
import android.view.View
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.application.MySharedPreferences
import com.example.updatedtrainingapp.dataBase.Constants
import com.example.updatedtrainingapp.dataBase.objects.ExerciseObject
import com.example.updatedtrainingapp.fragments.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_exercise_choice.*
import org.jetbrains.anko.backgroundColor
import javax.inject.Inject

@AndroidEntryPoint
class ExerciseChoiceFragment : BaseFragment(R.layout.fragment_exercise_choice),
    ExercisesChoiceAdapter.OnExerciseChoiceItemListener {

    @Inject
    lateinit var viewModel: ExerciseChoiceViewModel

    override fun onStart() {
        super.onStart()
        setAdapter()
    }

    override fun onExerciseChoiceItemClick(pair: Pair<String, String>, view: View) {
        addRemoveExercise(pair, view)
    }

    private fun addRemoveExercise(pair: Pair<String, String>, view: View) {
        viewModel.list.forEach { pairInList ->
            if (pairInList.first == pair.first) {
                viewModel.list.remove(pairInList)
                view.backgroundColor = Color.WHITE
                return
            }
        }
        viewModel.list.add(pair)
        view.backgroundColor = Color.BLUE
    }

    private fun setAdapter() {
        viewModel.getAllExercises()?.observe(viewLifecycleOwner, { exerciseList ->
            val list = MySharedPreferences.getList(Constants.EXERCISE_LIST)
            val newList = mutableListOf<ExerciseObject>()
            exerciseList.forEach {
                if (!list.contains(it.exerciseName)) {
                    newList.add(it)
                }
            }
            showExerciseList(
                viewModel.getList(newList)
            )
        })
    }

    private fun showExerciseList(
        exerciseList: MutableList<ExerciseObject>
    ) {
        val adapter =
            ExercisesChoiceAdapter()
        adapter.swapAdapter(exerciseList)
        adapter.setOnExerciseChoiceItemListener(this)
        exerciseChoiceRecyclerView.adapter = adapter
    }

    override fun onStop() {
        super.onStop()
        viewModel.list.forEach { i -> viewModel.addExercise(i) }
    }
}
