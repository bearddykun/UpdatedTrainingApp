package com.example.updatedtrainingapp.fragments.currentExercise

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.updatedtrainingapp.MainActivity
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.dataBase.objects.TrainingObject
import com.example.updatedtrainingapp.fragments.BaseFragment
import com.example.updatedtrainingapp.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.current_exercise_fragment.*
import org.jetbrains.anko.selector
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class CurrentExerciseFragment : BaseFragment(R.layout.current_exercise_fragment) {

    private val args: CurrentExerciseFragmentArgs by navArgs()
    private var adapter: CurrentExerciseAdapter? = null

    private val viewModel: CurrentExerciseViewModel by viewModels()
    private var trainingObject: TrainingObject = TrainingObject(null)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = CurrentExerciseAdapter()
        currentExerciseRV.adapter = adapter
        loadAdapter()
    }

    override fun onStart() {
        super.onStart()
        onClicks()
        exerciseNameBT.text = args.exName
    }

    private fun onClicks() {
        timerTextView.setOnClickListener {
            val timerList = activity?.resources?.getStringArray(R.array.timer_values)?.toList()
            timerList?.let {
                activity?.selector(
                    getString(R.string.set_timer), it
                ) { _, position ->
                    viewModel.setLastSetTime(TimeUnit.SECONDS.toMillis(it[position].toLong()))
                }
            }
            viewModel.getRemainingTime()?.removeObservers(viewLifecycleOwner)
            viewModel.resetTimer(activity = activity as MainActivity)
            timerTextView.text =
                TimeUnit.MILLISECONDS.toSeconds(viewModel.getLastSetTime()).toString()
        }

        timerButton.setOnClickListener {
            viewModel.getRemainingTime()?.removeObservers(viewLifecycleOwner)
            viewModel.resetTimer(activity = activity as MainActivity)
            viewModel.getRemainingTime()
                ?.observe(viewLifecycleOwner, { timerTextView.text = it.toString() })
            adapter?.updateList(currentExerciseKiloTIET.text.toString() + " X " + currentExerciseRepsTIET.text.toString())
            viewModel.updateProgressList(
                trainingObject,
                currentExerciseKiloTIET.text.toString(),
                currentExerciseRepsTIET.text.toString()
            )
        }
    }

    private fun loadAdapter() {
        viewModel.getExerciseWithDate(
            Utils.getNameWithDate(args.exName)
        )?.observe(viewLifecycleOwner, {
            if (it != null) {
                trainingObject = it
            }
            it?.let { list ->
                adapter?.swapAdapter(Utils.stringToList(list.exerciseText))
            }
        })
    }

    override fun onStop() {
        super.onStop()
        viewModel.updateProgressInDB(trainingObject)
    }
}
