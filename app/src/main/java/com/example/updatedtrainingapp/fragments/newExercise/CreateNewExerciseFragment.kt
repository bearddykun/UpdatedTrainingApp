package com.example.updatedtrainingapp.fragments.newExercise


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.updatedtrainingapp.MainActivity
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.databinding.FragmentCreateNewExerciseBinding
import com.example.updatedtrainingapp.fragments.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_create_new_exercise.*

@AndroidEntryPoint
class CreateNewExerciseFragment : BaseFragment(R.layout.fragment_create_new_exercise),
    CreateNewExerciseAdapter.OnCreateNewExerciseListener {

    private val viewModel: NewExerciseViewModel by viewModels()
    private var binding: FragmentCreateNewExerciseBinding? = null

    override fun onStart() {
        super.onStart()
        val adapter = CreateNewExerciseAdapter()
        adapter.setOnCreateNewExerciseListener(this)
        binding?.createNewExerciseRecyclerView?.adapter = adapter
        createNewExOnClick()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNewExerciseBinding.inflate(inflater, container, false)
        return binding?.root
    }

    private fun createNewExOnClick() {
        binding?.createExerciseBtn?.setOnClickListener {
            if (validateFields(
                    binding?.createExerciseNameEdit?.text.toString(),
                    binding?.createExerciseGroupText?.text.toString()
                )
            ) {
                isExerciseInside()
            }
        }
    }

    private fun isExerciseInside() {
        viewModel.getExerciseWithName(
            binding?.createExerciseNameEdit?.text.toString()
        ).observe(viewLifecycleOwner) {
            if (it == null) {
                viewModel.addExerciseToDb(createExerciseNameEdit.text.toString())
            } else {
                (activity as MainActivity).showErrorSnack("Already in DB")
            }
        }
    }

    override fun chooseMuscleGroup(imageId: Int, muscleGroup: String) {
        binding?.createExerciseGroupImage?.setImageResource(imageId)
        viewModel.chooseMuscleGroup(imageId, muscleGroup)
    }

    private fun validateFields(exName: String, exGroupName: String): Boolean {
        return when {
            exName == "" -> {
                (activity as MainActivity).showErrorSnack("No name")
                return false
            }
            exGroupName == "" -> {
                (activity as MainActivity).showErrorSnack("No group")
                return false
            }
            else -> true
        }
    }
}
