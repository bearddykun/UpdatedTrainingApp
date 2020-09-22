package com.example.updatedtrainingapp.fragments.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.updatedtrainingapp.R
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
        viewModel.getTrainingOnDate(Utils.getDate())?.observe(viewLifecycleOwner,
            {
                showViewsAndData(it)
            })
        super.onStart()
        binding?.calendar?.setOnDateChangeListener { _, year, month, dayOfMonth ->
            viewModel.getTrainingOnDate(viewModel.getDate(year, month, dayOfMonth))
                ?.observe(viewLifecycleOwner,
                    {
                        showViewsAndData(it)
                    })
        }
    }

    private fun showViewsAndData(list: List<TrainingObject>) {
        list.forEach {
            binding?.setHere?.text = it.exerciseText
            binding?.showComments?.text = it.comments
            binding?.showTotalWeight?.text = it.trainingWeight
            binding?.textInCalendar?.text = it.trainingName
            binding?.showWeight?.text = it.trainingWeight
            goneOrVisible(binding?.setHere)
            goneOrVisible(binding?.showComments)
            goneOrVisible(binding?.showTotalWeight)
            goneOrVisible(binding?.textInCalendar)
            goneOrVisible(binding?.showWeight)
        }
    }

    private fun goneOrVisible(textView: TextView?) {
        if (textView?.text.toString().isNotEmpty()) {
            textView?.visibility = View.VISIBLE
        } else {
            textView?.visibility = View.GONE
        }
    }
}