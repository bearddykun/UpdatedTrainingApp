package com.example.updatedtrainingapp.fragments.currentExercise

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.application.MySharedPreferences
import com.example.updatedtrainingapp.dataBase.Constants
import com.example.updatedtrainingapp.databinding.CurrentExerciseFragmentBinding
import com.example.updatedtrainingapp.fragments.BaseFragment
import com.example.updatedtrainingapp.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrentExerciseFragment : BaseFragment(R.layout.current_exercise_fragment) {

    private val channelId: String = "com.example.updatedtrainingapp.fragments.currentExercise"
    private val args: CurrentExerciseFragmentArgs by navArgs()
    private var adapter: CurrentExerciseAdapter? = null

    private val viewModel: CurrentExerciseViewModel by viewModels()
    private var binding: CurrentExerciseFragmentBinding? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = CurrentExerciseAdapter()
        binding?.currentExerciseRV?.adapter = adapter
        viewModel.updateTrainingObject(args.exName)
        loadAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CurrentExerciseFragmentBinding.inflate(inflater, container, false)
        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel
        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        onClicks()
        binding?.exerciseNameBT?.text = args.exName
        viewModel.getRemainingTime()
            .observe(viewLifecycleOwner, {
                if (it.toString() == "") {
                    sendNotification()
                }
            })

        viewModel.getMaxWeight().observe(viewLifecycleOwner, {
            if (it > MySharedPreferences.getInt(Constants.SAVE_MAX_WEIGHT + args.exName)) {
                binding?.maxWeightTV?.animate()?.translationX(-500f)?.translationY(500f)?.duration = 2000
                binding?.maxWeightTV?.setBackgroundColor(Color.RED)
                Handler(Looper.getMainLooper()).postDelayed(
                    {
                        viewModel.playUra(it)
                        binding?.maxWeightTV?.setBackgroundColor(Color.WHITE)
                        binding?.maxWeightTV?.animate()?.translationX(0f)
                            ?.translationY(0f)?.duration =
                            2000
                    }, 2000
                )
            }
        })
    }

    private fun onClicks() {
        binding?.timerTextView?.setOnClickListener {
            viewModel.resetTimer()
            Utils.getTimerChoiceDialog(requireActivity(), viewModel)
        }

        binding?.timerButton?.setOnClickListener {
            viewModel.resetTimer()
            viewModel.startTimer()

            if (binding?.currentExerciseKiloTIET?.text.toString()
                    .isNotEmpty() || binding?.currentExerciseRepsTIET?.text.toString().isNotEmpty()
            ) {
                adapter?.updateList(
                    "${binding?.currentExerciseKiloTIET?.text.toString()}  X  ${binding?.currentExerciseRepsTIET?.text.toString()}"
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
        viewModel.getExerciseWithDate(
            args.exName
        )?.observe(viewLifecycleOwner, {
            it?.let { trainingObject ->
                viewModel.setExistingTrainingObject(trainingObject)
                adapter?.swapAdapter(Utils.stringToList(trainingObject.exerciseText))
            }
        })
    }

    override fun onStop() {
        viewModel.insertProgressInDB()
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.resetTimer()
    }

    private fun sendNotification() {
        if (Build.VERSION.SDK_INT >= 26) {
            val channel = NotificationChannel(
                channelId,
                requireActivity().getString(R.string.notification_channel_id),
                NotificationManager.IMPORTANCE_DEFAULT
            )
            NotificationManagerCompat.from(requireActivity())
                .createNotificationChannel(channel)
            val builder =
                NotificationCompat.Builder(requireActivity(), channelId)
                    .setSmallIcon(R.drawable.ic_fitness_center_black_24dp)
                    .setContentTitle(requireActivity().getString(R.string.training))
                    .setContentText(requireActivity().getString(R.string.rest_over))
                    .setStyle(
                        NotificationCompat.BigTextStyle()
                            .bigText(requireActivity().getString(R.string.rest_over))
                    )
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            NotificationManagerCompat.from(requireActivity())
                .notify(1, builder.build())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
