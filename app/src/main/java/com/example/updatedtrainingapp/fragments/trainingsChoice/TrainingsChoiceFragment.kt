package com.example.updatedtrainingapp.fragments.trainingsChoice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.application.MySharedPreferences
import com.example.updatedtrainingapp.dataBase.Constants
import com.example.updatedtrainingapp.databinding.TrainingsChoiceFragmentBinding
import com.example.updatedtrainingapp.fragments.BaseFragment
import com.example.updatedtrainingapp.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrainingsChoiceFragment : BaseFragment(R.layout.trainings_choice_fragment),
    TrainingsAdapter.OnTrainingItemListener,
    TrainingsAdapter.OnTrainingItemLongListener {

    private val viewModel: TrainingsChoiceViewModel by viewModels()
    private var binding: TrainingsChoiceFragmentBinding? = null

    private fun startTraining(name: String) {
        viewModel.startTraining(name)
        findNavController().navigate(TrainingsChoiceFragmentDirections.actionFragmentThisTrainingFragmentToFragmentTrainingDay())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TrainingsChoiceFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onTrainingListItemClick(name: String) {
        Utils.getAlertDialog(
            requireActivity(),
            "${getString(R.string.start_training)} $name?",
            getString(R.string.start_training_timer)
        )
        {
            startTraining(name)
        }
    }

    override fun onTrainingListItemLongClick(name: String) {
        Utils.getAlertDialog(
            requireActivity(),
            getString(R.string.delete),
            getString(R.string.delete_training)
        )
        {
            viewModel.deleteAndUpdate(name)
        }
    }

    override fun onStart() {
        super.onStart()
        binding?.addTrainingBtn?.setOnClickListener {
            findNavController().navigate(TrainingsChoiceFragmentDirections.actionFragmentThisTrainingFragmentToFragmentCreateTraining())
        }
        setAdapter()
    }

    private fun setAdapter() {
        val adapter = TrainingsAdapter()
        adapter.swapAdapter(MySharedPreferences.getList(Constants.SAVE_NEW_TRAINING_LIST).toList())
        adapter.setOnTrainingItemListener(this)
        adapter.setOnTrainingItemLongListener(this)
        binding?.trainingRecyclerView?.adapter = adapter
    }
}


