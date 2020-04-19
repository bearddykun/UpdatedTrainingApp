package com.example.updatedtrainingapp.fragments.newExercise


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.updatedtrainingapp.MainActivity
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.application.ThisApplication
import com.example.updatedtrainingapp.dataBase.dbViewModels.ExerciseDBViewModel
import com.example.updatedtrainingapp.dataBase.objects.ExerciseObject
import kotlinx.android.synthetic.main.fragment_create_new_exercise.*
import javax.inject.Inject

class CreateNewExerciseFragment : Fragment(R.layout.fragment_create_new_exercise),
    CreateNewExerciseAdapter.OnCreateNewExerciseListener {

    @Inject
    lateinit var exerciseDBViewModel: ExerciseDBViewModel
    private var exImageId = 0
    private var exGroupName = ""
    private var isExerciseInside = false

    override fun onStart() {
        super.onStart()
        val adapter = CreateNewExerciseAdapter()
        adapter.setOnCreateNewExerciseListener(this)
        createNewExerciseRecyclerView.adapter = adapter
        createNewExOnClick()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as ThisApplication).appComponent.inject(this)
    }

    private fun createNewExOnClick() {
        createExerciseBtn.setOnClickListener {
            addExerciseToDb(
                isExerciseInside(),
                createExerciseNameEdit.text.toString(), exGroupName, exImageId
            )
        }
    }

    private fun isExerciseInside(): Boolean {
        exerciseDBViewModel.getExerciseWithName(
            createExerciseNameEdit.text.toString()
        ).observe(viewLifecycleOwner, Observer {
            isExerciseInside = it != null
        })
        return isExerciseInside
    }


    override fun chooseMuscleGroup(imageId: Int, muscleGroup: String) {
        exImageId = imageId
        exGroupName = muscleGroup
    }

    private fun addExerciseToDb(
        isExerciseInside: Boolean,
        exName: String,
        exGroupName: String,
        exImageId: Int
    ) {
        if (isExerciseInside) {
            (activity as MainActivity).showErrorSnack("Already in DB")
            return
        }
        if (exName == "" || exGroupName == "" || exImageId == 0) {
            validateFields(exName, exGroupName, exImageId)
            return
        }
        val exercise = ExerciseObject(null, exName, exGroupName, exImageId.toString())
        exerciseDBViewModel.insertExercise(exercise)
    }

    private fun validateFields(exName: String, exGroupName: String, exImageId: Int) {
        when {
            exName == "" -> (activity as MainActivity).showErrorSnack("No name")
            exGroupName == "" -> (activity as MainActivity).showErrorSnack("No group")
            exImageId == 0 -> (activity as MainActivity).showErrorSnack("No image")
        }
    }
}
