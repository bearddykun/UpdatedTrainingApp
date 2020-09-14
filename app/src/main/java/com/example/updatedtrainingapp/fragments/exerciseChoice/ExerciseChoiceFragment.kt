package com.example.updatedtrainingapp.fragments.exerciseChoice

import android.graphics.Color
import android.view.View
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.application.MySharedPreferences
import com.example.updatedtrainingapp.dataBase.Constants
import com.example.updatedtrainingapp.dataBase.dbViewModels.ExerciseDBViewModel
import com.example.updatedtrainingapp.dataBase.dbViewModels.TrainingDBViewModel
import com.example.updatedtrainingapp.dataBase.objects.ExerciseObject
import com.example.updatedtrainingapp.dataBase.objects.TrainingObject
import com.example.updatedtrainingapp.fragments.BaseFragment
import com.example.updatedtrainingapp.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_exercise_choice.*
import org.jetbrains.anko.backgroundColor
import javax.inject.Inject

@AndroidEntryPoint
class ExerciseChoiceFragment : BaseFragment(R.layout.fragment_exercise_choice),
    ExercisesChoiceAdapter.OnExerciseChoiceItemListener {

    private var list: MutableSet<String> = mutableSetOf()

    @Inject
    lateinit var exerciseDBViewModel: ExerciseDBViewModel

    @Inject
    lateinit var trainingViewModel: TrainingDBViewModel

    override fun onStart() {
        super.onStart()
        setAdapter()
    }

    override fun onExerciseChoiceItemClick(trainingName: String, view: View) {
        addRemoveExercise(trainingName, view)
    }

    private fun addRemoveExercise(exerciseName: String, view: View) {
        if (list.contains(exerciseName)) {
            list.remove(exerciseName)
            view.backgroundColor = Color.WHITE
        } else {
            list.add(exerciseName)
            view.backgroundColor = Color.BLUE
        }
    }

    private fun setAdapter() {
        exerciseDBViewModel.getAllExercises()?.observe(viewLifecycleOwner, { exerciseList ->
            showExerciseList(
                getList(exerciseList)
            )
        })
    }

    private fun showExerciseList(
        exerciseList: MutableList<ExerciseObject>
    ) {
        val adapter = ExercisesChoiceAdapter()
        adapter.swapAdapter(exerciseList)
        adapter.setOnExerciseChoiceItemListener(this)
        exerciseChoiceRecyclerView.adapter = adapter
    }

    private fun addExercise(exerciseName: String) {
        trainingViewModel.insertExercise(
            TrainingObject(
                null,
                exerciseName = exerciseName,
                trainingName = MySharedPreferences.getString(Constants.SAVE_TRAINING_NAME),
            )
        )
    }

    private fun getList(exerciseList: List<ExerciseObject>): MutableList<ExerciseObject> {
        val oldList =
            Utils.stringToList(
                MySharedPreferences.getString(
                    Utils.getCurrentTrainingList()
                )
            )
        val newExList: MutableList<ExerciseObject> = mutableListOf()
        for (i in exerciseList) {
            if (!oldList.contains(i.exerciseName)) {
                newExList.add(i)
            }
        }
        return newExList
    }

    override fun onStop() {
        super.onStop()
        //FIXME duplicate items still possible
        list.forEach { i -> addExercise(i) }
    }
}
