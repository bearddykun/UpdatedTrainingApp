package com.example.updatedtrainingapp.fragments.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.dataBase.objects.CalendarObject
import com.example.updatedtrainingapp.dataBase.objects.TrainingObject
import com.example.updatedtrainingapp.databinding.FragmentCalendarBinding
import com.example.updatedtrainingapp.fragments.BaseFragment
import com.example.updatedtrainingapp.utils.Utils

class CalendarFragment : BaseFragment(R.layout.fragment_calendar) {

    private val viewModel: CalendarViewModel by activityViewModels()
    private var binding: FragmentCalendarBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getTrainingOnDate(Utils.getDate())?.observe(viewLifecycleOwner,
            {
                showViewsAndData(it)
            })
        binding?.calendar?.setOnDateChangeListener { _, year, month, dayOfMonth ->
            showViewsAndData(mutableListOf())
            viewModel.getTrainingOnDate(viewModel.getDate(year, month, dayOfMonth))
                ?.observe(viewLifecycleOwner,
                    {
                        showViewsAndData(it)
                    })
        }
    }

    private fun showViewsAndData(list: List<TrainingObject>) {
        val exerciseDataList = mutableListOf<String>()
        val exerciseCommentList = mutableListOf<String>()
        val exerciseWeightList = mutableListOf<String>()
        var trainingName = ""
        list.forEach {
            exerciseDataList.add("${it.exerciseName} \n\n${it.exerciseText}\n\n")
            exerciseCommentList.add(it.comments)
            exerciseWeightList.add(it.trainingWeight)
            trainingName = it.trainingName
        }
        var exerciseData = ""
        var exerciseComment = ""
        var exerciseWeight = ""

        exerciseDataList.forEach { exerciseData += it }
        exerciseCommentList.forEach { exerciseComment += it }
        exerciseWeightList.forEach { exerciseWeight += it }

        val calendarObject = CalendarObject(trainingName = trainingName, exerciseData = exerciseData,
        exerciseComment = exerciseComment, exerciseTotalWeight = exerciseWeight)
        binding?.exerciseDataTV?.text = calendarObject.exerciseData
        binding?.showComments?.text = calendarObject.exerciseComment
        binding?.showTotalWeight?.text = calendarObject.exerciseTotalWeight
        binding?.textInCalendar?.text = calendarObject.trainingName

        goneOrVisible(binding?.exerciseDataTV)
        goneOrVisible(binding?.showComments)
        goneOrVisible(binding?.showTotalWeight)
        goneOrVisible(binding?.textInCalendar)
        goneOrVisible(binding?.showWeight)
    }

    private fun goneOrVisible(textView: TextView?) {
        if (textView?.text.toString().isNotEmpty()) {
            textView?.visibility = View.VISIBLE
        } else {
            textView?.visibility = View.GONE
        }
    }
}