package com.example.updatedtrainingapp.fragments.newExercise


import androidx.fragment.app.viewModels
import com.example.updatedtrainingapp.MainActivity
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.dataBase.dbViewModels.ExerciseDBViewModel
import com.example.updatedtrainingapp.dataBase.objects.ExerciseObject
import com.example.updatedtrainingapp.fragments.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_create_new_exercise.*

@AndroidEntryPoint
class CreateNewExerciseFragment : BaseFragment(R.layout.fragment_create_new_exercise),
    CreateNewExerciseAdapter.OnCreateNewExerciseListener {

    private val exerciseDBViewModel: ExerciseDBViewModel by viewModels()
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
        )?.observe(viewLifecycleOwner, {
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
