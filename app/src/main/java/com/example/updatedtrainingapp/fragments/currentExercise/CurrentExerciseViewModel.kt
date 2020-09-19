package com.example.updatedtrainingapp.fragments.currentExercise

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.CountDownTimer
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.updatedtrainingapp.MainActivity
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.application.MySharedPreferences
import com.example.updatedtrainingapp.dataBase.Constants
import com.example.updatedtrainingapp.dataBase.dbViewModels.TrainingDBViewModel
import com.example.updatedtrainingapp.dataBase.objects.TrainingObject
import com.example.updatedtrainingapp.utils.Utils
import org.jetbrains.anko.doAsync
import java.util.concurrent.TimeUnit

class CurrentExerciseViewModel @ViewModelInject constructor(
    private val trainingViewModel: TrainingDBViewModel
) : ViewModel() {

    var trainingObject: TrainingObject = TrainingObject(
        null,
        trainingNameWithDate = Utils.getNameWithDate(MySharedPreferences.getString(Constants.SAVE_TRAINING_NAME))
    )
    private val channelId: String = "com.example.updatedtrainingapp.fragments.currentExercise"
    private var remainingTime: MutableLiveData<String> = MutableLiveData("60")
    private var lastSetTime: String = "60"
    private var timer: CountDownTimer? = null

    private fun sendNotification(context: Context) {
        if (Build.VERSION.SDK_INT >= 26) {
            val channel = NotificationChannel(
                channelId,
                context.getString(R.string.notification_channel_id),
                NotificationManager.IMPORTANCE_DEFAULT
            )
            NotificationManagerCompat.from(context)
                .createNotificationChannel(channel)
            val builder =
                NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(R.drawable.ic_fitness_center_black_24dp)
                    .setContentTitle(context.getString(R.string.training))
                    .setContentText(context.getString(R.string.rest_over))
                    .setStyle(
                        NotificationCompat.BigTextStyle()
                            .bigText(context.getString(R.string.rest_over))
                    )
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            NotificationManagerCompat.from(context)
                .notify(1, builder.build())
        }
    }

    fun getRemainingTime(): MutableLiveData<String>? {
        return remainingTime
    }

    private fun setTimer(time: Long, activity: MainActivity) {
        timer = object : CountDownTimer(time, TimeUnit.SECONDS.toMillis(1)) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTime.value =
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished).toString()
            }

            override fun onFinish() {
                sendNotification(activity)
            }
        }
    }

    fun startTimer() {
        timer?.start()
    }

    private fun sendNotification(activity: MainActivity) {
        activity.soundManager.playPTCD()
        activity.applicationContext?.let { sendNotification(activity.applicationContext) }
    }

    fun resetTimer(activity: MainActivity) {
        timer?.cancel()
        remainingTime.value = lastSetTime
        setTimer(TimeUnit.SECONDS.toMillis(lastSetTime.toLong()), activity)
    }

    fun getExerciseWithDate(exName: String): LiveData<TrainingObject>? {
        return trainingViewModel.getExerciseWithData(
            exName,
            Utils.getNameWithDate(MySharedPreferences.getString(Constants.SAVE_TRAINING_NAME))
        )
    }

    fun updateExerciseText(
        trainingObject: TrainingObject,
        kiloText: String,
        repsText: String
    ) {
        val text = if (trainingObject.exerciseText.isNotEmpty()) {
            " , $kiloText X $repsText"
        } else {
            "$kiloText X $repsText"
        }
        trainingObject.exerciseText += text
    }

    fun updateProgressInDB(trainingObject: TrainingObject) {
        doAsync {
            trainingViewModel.isExerciseInThisTraining(
                trainingObject.exerciseName,
                trainingObject.trainingNameWithDate
            )?.let {
                if (it) {
                    trainingViewModel.updateExercise(trainingObject = trainingObject)
                } else {
                    trainingViewModel.insertExercise(trainingObject = trainingObject)
                }
            }
        }
    }

    fun setLastSetTime(time: String) {
        lastSetTime = time
    }
}
