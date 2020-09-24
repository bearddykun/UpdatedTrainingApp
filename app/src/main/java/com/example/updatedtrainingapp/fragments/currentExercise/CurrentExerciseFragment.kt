package com.example.updatedtrainingapp.fragments.currentExercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.databinding.CurrentExerciseFragmentBinding
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
    private var binding: CurrentExerciseFragmentBinding? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = CurrentExerciseAdapter()
        binding?.currentExerciseRV?.adapter = adapter
        loadAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CurrentExerciseFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.setTrainingObjectData(args.exName)
        onClicks()
        binding?.exerciseNameBT?.text = args.exName
        viewModel.getRemainingTime()
            ?.observe(viewLifecycleOwner, { timerTextView.text = it.toString() })
    }

    private fun onClicks() {
        binding?.timerTextView?.setOnClickListener {
            viewModel.resetTimer()
            val timerList = activity?.resources?.getStringArray(R.array.timer_values)?.toList()
            timerList?.let {
                activity?.selector(
                    getString(R.string.set_timer), it
                ) { _, position ->
                    viewModel.setLastSetTime(it[position])
                    binding?.timerTextView?.text = it[position]
                }
            }
        }

        binding?.timerButton?.setOnClickListener {
            viewModel.resetTimer()
            viewModel.startTimer()

            if (binding?.currentExerciseKiloTIET?.text.toString()
                    .isNotEmpty() || binding?.currentExerciseRepsTIET?.text.toString().isNotEmpty()
            ) {
                adapter?.updateList(
                    binding?.currentExerciseKiloTIET?.text.toString() + " X " +
                            binding?.currentExerciseRepsTIET?.text.toString()
                )
                viewModel.updateExerciseText(
                    binding?.currentExerciseKiloTIET?.text.toString(),
                    binding?.currentExerciseRepsTIET?.text.toString()
                )
            }
        }

        binding?.exerciseNameBT?.setOnClickListener {
            findNavController().navigate(
                CurrentExerciseFragmentDirections.actionCurrentExerciseFragmentToFragmentTrainingDay()
            )
        }
    }

    private fun loadAdapter() {
        viewModel.getExerciseInTraining(
        )?.observe(viewLifecycleOwner, {
            it?.let { trainingObject ->
                it.realDate = Utils.getDate()
                viewModel.trainingObject = it
                adapter?.swapAdapter(Utils.stringToList(trainingObject.exerciseText))
            }
        })
    }

    override fun onStop() {
        var inside = false
        viewModel.getExerciseWithDate()?.observe(viewLifecycleOwner, {
            inside = true
        })
        viewModel.updateProgressInDB(inside)
        super.onStop()

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.resetTimer()
    }
}
