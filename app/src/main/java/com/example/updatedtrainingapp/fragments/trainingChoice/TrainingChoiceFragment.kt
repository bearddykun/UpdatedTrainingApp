package com.example.updatedtrainingapp.fragments.trainingChoice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.updatedtrainingapp.MainActivity
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.application.MySharedPreferences
import com.example.updatedtrainingapp.application.ThisApplication
import com.example.updatedtrainingapp.dataBase.Constants
import com.example.updatedtrainingapp.dataBase.TrainingViewModel
import com.example.updatedtrainingapp.dataBase.objects.TrainingObject
import kotlinx.android.synthetic.main.this_training_fragment.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton
import javax.inject.Inject

class TrainingChoiceFragment @Inject constructor() : Fragment(),
    TrainingAdapter.OnTrainingItemListener,
    TrainingAdapter.OnTrainingItemLongListener {

    @Inject
    lateinit var trainingViewModel: TrainingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.this_training_fragment, container, false)
    }

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
        findNavController().navigate(TrainingChoiceFragmentDirections.actionFragmentThisTrainingFragmentToFragmentTrainingDay())
    }

    override fun onTrainingListItemClick(name: String) {
        activity?.alert("Start Training", "Start training timer?") {
            yesButton {
                MySharedPreferences.saveString(Constants.SAVE_TRAINING_NAME, name)
                startTraining()
            }
            noButton { }
        }?.show()
    }

    override fun onTrainingListItemLongClick(name: String) {
        activity?.alert("Delete", "Want to delete this Training?") {
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
            findNavController().navigate(TrainingChoiceFragmentDirections.actionFragmentThisTrainingFragmentToFragmentCreateTraining())
        }
        setAdapter()
    }

    private fun setAdapter() {
        val adapter = TrainingAdapter()
        trainingViewModel.getTrainings().observe(
            viewLifecycleOwner,
            Observer<List<TrainingObject>> { trainingList -> adapter.swapAdapter(trainingList!!) })
        adapter.setOnTrainingItemListener(this)
        adapter.setOnTrainingItemLongListener(this)
        trainingRecyclerView.adapter = adapter
    }
}


