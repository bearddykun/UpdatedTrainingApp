package com.example.updatedtrainingapp.fragments.exerciseChoice


import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.application.MySharedPreferences
import com.example.updatedtrainingapp.application.ThisApplication
import com.example.updatedtrainingapp.dataBase.Constants
import com.example.updatedtrainingapp.dataBase.ExerciseViewModel
import com.example.updatedtrainingapp.dataBase.TrainingViewModel
import com.example.updatedtrainingapp.dataBase.objects.ExerciseObject
import com.example.updatedtrainingapp.dataBase.objects.TrainingObject
import com.example.updatedtrainingapp.utils.Utils
import com.iamagamedev.trainingapp.ui.thisTraining.fragments.exerciseChoice.ExercisesChoiceAdapter
import kotlinx.android.synthetic.main.fragment_exercise_choice.*
import org.jetbrains.anko.backgroundColor
import javax.inject.Inject

class ExerciseChoiceFragment : Fragment(R.layout.fragment_exercise_choice),
    ExercisesChoiceAdapter.OnExerciseChoiceItemListener {

    private val list: MutableList<String> = mutableListOf()
    @Inject
    lateinit var exerciseViewModel: ExerciseViewModel
    @Inject
    lateinit var trainingViewModel: TrainingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as ThisApplication).appComponent.inject(this)
    }

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
        addToTraining()
    }

    private fun setAdapter() {
        val adapter = ExercisesChoiceAdapter()
        exerciseViewModel.getAllExercises().observe(viewLifecycleOwner, Observer { exerciseList ->
            adapter.swapAdapter(getList(exerciseList!!))
        })
        adapter.setOnExerciseChoiceItemListener(this)
        exerciseChoiceRecyclerView.adapter = adapter
    }

    private fun addToTraining() {
        trainingViewModel.getTraining(MySharedPreferences.getString(Constants.SAVE_TRAINING_NAME))
            .observe(viewLifecycleOwner,
                Observer<TrainingObject> { training ->
                    training?.trainingExerciseNameList = buildString()
                    training?.let { trainingViewModel.updateTraining(it) }
                })
    }

    private fun getList(exerciseList: List<ExerciseObject>): List<ExerciseObject> {
        val oldList =
            Utils.stringToList(MySharedPreferences.getString(Utils.getCurrentTrainingList()))
        val newExList: MutableList<ExerciseObject> = mutableListOf()
        for (i in exerciseList) {
            if (!oldList.contains(i.exerciseName)) {
                newExList.add(i)
            }
        }
        return newExList
    }

    private fun buildString(): String {
        return if (MySharedPreferences.isInside(Utils.getCurrentTrainingList())) {
            val sb = StringBuilder()
            sb.append(MySharedPreferences.getString(Utils.getCurrentTrainingList()))
            sb.append(" , ")
            sb.append(Utils.listToString(list))
            sb.toString()
        } else {
            Utils.listToString(list)
        }
    }
}
