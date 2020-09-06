package com.example.updatedtrainingapp.fragments.currentExercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.updatedtrainingapp.MainActivity
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.application.MySharedPreferences
import com.example.updatedtrainingapp.dataBase.Constants
import com.example.updatedtrainingapp.dataBase.objects.ExerciseInfoObject
import com.example.updatedtrainingapp.fragments.BaseFragment
import com.example.updatedtrainingapp.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.current_exercise_fragment.*
import org.jetbrains.anko.selector

@AndroidEntryPoint
class CurrentExerciseFragment : BaseFragment(R.layout.current_exercise_fragment) {

    private val args: CurrentExerciseFragmentArgs by navArgs()
    private var adapter: CurrentExerciseAdapter? = null
    private var exerciseInfoObject: ExerciseInfoObject = ExerciseInfoObject(null)

    private val viewModel: CurrentExerciseViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = CurrentExerciseAdapter()
        loadAdapter()
    }

    override fun onStart() {
        super.onStart()
        onClicks()
        exerciseNameBT.text = args.exName
    }

    private fun onClicks() {
        timerTextView.setOnClickListener {
            val timerList = activity?.resources?.getStringArray(R.array.timer_values)!!.toList()
            activity?.selector(
                getString(R.string.set_timer), timerList
            ) { _, position ->
                viewModel.getRemainingTime()?.removeObservers(viewLifecycleOwner)
                viewModel.stopTimer()
                timerTextView.text = timerList[position]
            }
        }

        timerButton.setOnClickListener {
            viewModel.getRemainingTime()
                ?.observe(viewLifecycleOwner, Observer { timerTextView.text = it.toString() })
            val time = timerTextView.text.toString().toLong() * 1000
            viewModel.setTimer(time = time, activity = activity as MainActivity)
            viewModel.updateExerciseInfoDataBase(
                exerciseInfoObject,
                currentExerciseKiloTIET.text.toString(),
                currentExerciseRepsTIET.text.toString()
            )
        }
    }

    private fun loadAdapter() {
        viewModel.getExerciseData(
            args.exName,
            MySharedPreferences.getString(Constants.SAVE_TRAINING_NAME)
        )
            ?.observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    exerciseInfoObject = it
                }
                adapter?.swapAdapter(Utils.stringToList(exerciseInfoObject.exerciseReps))
            })
    }
}
