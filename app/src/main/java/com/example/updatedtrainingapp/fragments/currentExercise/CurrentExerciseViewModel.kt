package com.example.updatedtrainingapp.fragments.currentExercise

import android.app.Application
import android.os.CountDownTimer
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.updatedtrainingapp.application.MySharedPreferences
import com.example.updatedtrainingapp.dataBase.Constants
import com.example.updatedtrainingapp.dataBase.dbViewModels.TrainingDBViewModel
import com.example.updatedtrainingapp.dataBase.objects.TrainingObject
import com.example.updatedtrainingapp.utils.SoundManager
import com.example.updatedtrainingapp.utils.Utils
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class CurrentExerciseViewModel @ViewModelInject constructor(
    application: Application, private val trainingViewModel: TrainingDBViewModel,
    private val soundManager: SoundManager
) : AndroidViewModel(application) {

    private var trainingObject: TrainingObject = TrainingObject(null)
    private var remainingTimerTime: MutableLiveData<String> = MutableLiveData("60")
    private var lastSetTime: String = "60"
    private var timer: CountDownTimer? = null
    private var isUpdate = false

    fun getRemainingTime(): MutableLiveData<String> {
        return remainingTimerTime
    }

    private fun setTimer(time: Long) {
        timer = object : CountDownTimer(time, TimeUnit.SECONDS.toMillis(1)) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimerTime.value =
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished).toString()
            }

            override fun onFinish() {
                sendNotificationWithSound()
            }
        }
    }

    fun startTimer() {
        timer?.start()
    }

    private fun sendNotificationWithSound() {
        soundManager.playPTCD()
    }

    fun resetTimer() {
        timer?.cancel()
        remainingTimerTime.value = lastSetTime
        setTimer(TimeUnit.SECONDS.toMillis(lastSetTime.toLong()))
    }

    fun getExerciseWithDate(exName: String): LiveData<TrainingObject>? {
        return trainingViewModel.isExerciseInThisTraining(
            exName,
            Utils.getDate(),
            MySharedPreferences.getString(Constants.SAVE_TRAINING_NAME)
        )
    }

    fun updateExerciseText(
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

    fun insertProgressInDB() {
        viewModelScope.launch {
            if (isUpdate) {
                trainingViewModel.updateExercise(trainingObject = trainingObject)
            } else {
                trainingViewModel.insertExercise(trainingObject = trainingObject)
            }
        }
    }

    fun setLastSetTime(time: String) {
        lastSetTime = time
    }

    fun setExistingTrainingObject(trainingObject: TrainingObject) {
        this.trainingObject = trainingObject
        isUpdate = true
    }
}
