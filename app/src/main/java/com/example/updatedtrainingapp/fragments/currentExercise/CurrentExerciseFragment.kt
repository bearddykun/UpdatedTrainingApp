package com.example.updatedtrainingapp.fragments.currentExercise

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.updatedtrainingapp.MainActivity
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.fragments.BaseFragment
import com.example.updatedtrainingapp.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.current_exercise_fragment.*
import org.jetbrains.anko.selector

@AndroidEntryPoint
class CurrentExerciseFragment : BaseFragment(R.layout.current_exercise_fragment) {

    private val args: CurrentExerciseFragmentArgs by navArgs()
    private var adapter: CurrentExerciseAdapter? = null

    private val viewModel: CurrentExerciseViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = CurrentExerciseAdapter()
        currentExerciseRV.adapter = adapter
        loadAdapter()
    }

    override fun onStart() {
        super.onStart()
        onClicks()
        viewModel.trainingObject.exerciseName = args.exName
        exerciseNameBT.text = args.exName
        viewModel.getRemainingTime()
            ?.observe(viewLifecycleOwner, { timerTextView.text = it.toString() })
    }

    private fun onClicks() {
        timerTextView.setOnClickListener {
            viewModel.resetTimer(activity = activity as MainActivity)
            val timerList = activity?.resources?.getStringArray(R.array.timer_values)?.toList()
            timerList?.let {
                activity?.selector(
                    getString(R.string.set_timer), it
                ) { _, position ->
                    viewModel.setLastSetTime(it[position])
                    timerTextView.text = it[position]
                }
            }
        }

        timerButton.setOnClickListener {
            viewModel.resetTimer(activity = activity as MainActivity)
            viewModel.startTimer()

            if (currentExerciseKiloTIET.text.toString()
                    .isNotEmpty() || currentExerciseRepsTIET.text.toString().isNotEmpty()
            ) {
                adapter?.updateList(currentExerciseKiloTIET.text.toString() + " X " + currentExerciseRepsTIET.text.toString())
                viewModel.updateExerciseText(
                    viewModel.trainingObject,
                    currentExerciseKiloTIET.text.toString(),
                    currentExerciseRepsTIET.text.toString()
                )
            }
        }

        exerciseNameBT.setOnClickListener {
            findNavController().navigate(
                CurrentExerciseFragmentDirections.actionCurrentExerciseFragmentToFragmentTrainingDay()
            )
        }
    }

    private fun loadAdapter() {
        viewModel.getExerciseWithDate(
            args.exName
        )?.observe(viewLifecycleOwner, {
            if (it != null) {
                viewModel.trainingObject = it
            }
            it?.let { list ->
                adapter?.swapAdapter(Utils.stringToList(list.exerciseText))
            }
        })
    }

    override fun onStop() {
        super.onStop()
        viewModel.updateProgressInDB(viewModel.trainingObject)
    }
}
