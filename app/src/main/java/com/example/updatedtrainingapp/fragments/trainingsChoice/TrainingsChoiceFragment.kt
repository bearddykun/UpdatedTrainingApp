package com.example.updatedtrainingapp.fragments.trainingsChoice

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.updatedtrainingapp.MainActivity
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.application.MySharedPreferences
import com.example.updatedtrainingapp.application.ThisApplication
import com.example.updatedtrainingapp.dataBase.Constants
import com.example.updatedtrainingapp.dataBase.dbViewModels.TrainingDBViewModel
import com.example.updatedtrainingapp.dataBase.objects.TrainingObject
import com.example.updatedtrainingapp.utils.Utils
import kotlinx.android.synthetic.main.trainings_choice_fragment.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton
import javax.inject.Inject

class TrainingsChoiceFragment @Inject constructor() : Fragment(R.layout.trainings_choice_fragment),
    TrainingsAdapter.OnTrainingItemListener,
    TrainingsAdapter.OnTrainingItemLongListener {

    @Inject
    lateinit var trainingViewModel: TrainingDBViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.application as ThisApplication).appComponent.inject(this)
    }

    fun updateList(trainingName: String) {
        trainingViewModel.insertTraining(TrainingObject(null, trainingName))
    }

    private fun deleteTraining(training: TrainingObject) {
        if (training.trainingName == Constants.DEFAULT_SET) {
            (activity as MainActivity).showErrorSnack("Sorry, can't delete ${Constants.DEFAULT_SET}")
        }
    }

    private fun startTraining() {
        if (!MySharedPreferences.isInside(Constants.SAVE_START_TIME)) {
            val startTime = System.currentTimeMillis()
            MySharedPreferences.saveLong(Constants.SAVE_START_TIME, startTime)
        }
        findNavController().navigate(TrainingsChoiceFragmentDirections.actionFragmentThisTrainingFragmentToFragmentTrainingDay())
    }

    override fun onTrainingListItemClick(name: String) {
        activity?.alert(getString(R.string.start_training), getString(R.string.strat_timer)) {
            yesButton {
                MySharedPreferences.saveString(
                    Constants.SAVE_TRAINING_NAME,
                    Utils.getTrainingNameWithDate(name)
                )
                trainingViewModel.insertTraining(
                    TrainingObject(
                        null,
                        name,
                        Utils.getTrainingNameWithDate(name)
                    )
                )
                startTraining()
            }
            noButton { }
        }?.show()
    }

    override fun onTrainingListItemLongClick(name: String) {
        activity?.alert(getString(R.string.delete), getString(R.string.delete_training)) {
            yesButton {
                (trainingViewModel.getTraining(name).observe(viewLifecycleOwner,
                    Observer { training -> deleteTraining(training) }))
            }
            noButton { }
        }?.show()
    }

    override fun onStart() {
        super.onStart()
        addTrainingBtn.setOnClickListener {
            findNavController().navigate(TrainingsChoiceFragmentDirections.actionFragmentThisTrainingFragmentToFragmentCreateTraining())
        }
        setAdapter()
    }

    private fun setAdapter() {
        val adapter = TrainingsAdapter()
        trainingViewModel.getTrainings().observe(
            viewLifecycleOwner,
            Observer { trainingList ->
                val trainingListNoDuplicates = trainingList?.toSet()
                adapter.swapAdapter(trainingListNoDuplicates!!.toList())
            })
        adapter.setOnTrainingItemListener(this)
        adapter.setOnTrainingItemLongListener(this)
        trainingRecyclerView.adapter = adapter
    }
}


