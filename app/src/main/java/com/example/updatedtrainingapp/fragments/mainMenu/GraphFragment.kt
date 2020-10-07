package com.example.updatedtrainingapp.fragments.mainMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.databinding.FragmentMainGrapItemBinding
import com.example.updatedtrainingapp.fragments.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_PARAM1 = "param1"

@AndroidEntryPoint
class GraphFragment : BaseFragment(R.layout.fragment_main_grap_item) {

    private var trainingName: String? = null
    private val viewModel: MainMenuViewModel by activityViewModels()
    private var binding: FragmentMainGrapItemBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trainingName = arguments?.getString(ARG_PARAM1)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainGrapItemBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTrainingWithName(trainingName ?: "")
            ?.observe(viewLifecycleOwner, {
                if (!it.isNullOrEmpty())
                    binding?.graphView?.addSeries(viewModel.prepareStatistics(it))
            })
        binding?.textMainGroup?.text = trainingName
    }

    companion object {
        fun newInstance(trainingName: String): GraphFragment {
            val mainFragment = GraphFragment()
            val bundle = Bundle()

            bundle.putString(ARG_PARAM1, trainingName)
            mainFragment.arguments = bundle

            return mainFragment
        }
    }
}