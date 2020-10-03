package com.example.updatedtrainingapp.utils

import android.app.AlertDialog
import android.content.Context
import android.os.Build
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.application.MySharedPreferences
import com.example.updatedtrainingapp.dataBase.Constants
import com.example.updatedtrainingapp.fragments.currentExercise.CurrentExerciseViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class Utils {

    companion object {
        private fun listToString(list: MutableList<String>): String {
            val stringBuilder = StringBuilder()
            for (i in 0 until list.size) {
                if (list[i] != "") {
                    if (i != list.size - 1)
                        stringBuilder.append("${list[i]} , ")
                    else
                        stringBuilder.append(list[i])
                }
            }
            return stringBuilder.toString()
        }

        fun stringToList(string: String): MutableList<String> {
            return if (string != "")
                string.split(" , ").toMutableList()
            else
                mutableListOf()
        }

        fun getTrainingExerciseList(): MutableList<String> {
            return stringToList(
                MySharedPreferences.getString(
                    MySharedPreferences.getString(
                        Constants.SAVE_TRAINING_NAME
                    )
                )
            )
        }

        fun saveTrainingExerciseString(list: MutableList<String>) {
            MySharedPreferences.saveString(
                MySharedPreferences.getString(Constants.SAVE_TRAINING_NAME),
                listToString(list)
            )
        }

        fun getCurrentTime(): String {
            val calendar = Calendar.getInstance()
            val date = calendar.time
            val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.UK)
            return dateFormat.format(date)
        }

        fun getNameWithDate(name: String): String {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                name + LocalDate.now().toString()
            } else {
                val date = Calendar.getInstance().time
                val dateFormat = SimpleDateFormat("YYYY-MM-dd", Locale.UK)
                name + dateFormat.format(date)
            }
        }

        fun getDate(): String {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocalDate.now().toString().replace("-", "")
            } else {
                val date = Calendar.getInstance().time
                val dateFormat = SimpleDateFormat("YYYYMMdd", Locale.UK)
                dateFormat.format(date)
            }
        }

        fun getAlertDialog(context: Context, title: String, message: String, func: () -> Unit) {
            val alert = AlertDialog.Builder(context)
            alert.apply {
                setMessage(message)
                setTitle(title)
                setPositiveButton("OK") { _, _ ->
                    func()
                }
                setNegativeButton("NO") { _, _ -> }
            }.show()
        }

        fun getTimerChoiceDialog(context: Context, viewModel: CurrentExerciseViewModel) {
            val array = context.resources?.getStringArray(R.array.timer_values)
            array?.let {
                val alertDialog = AlertDialog.Builder(context)
                alertDialog.apply {
                    setTitle(context.getString(R.string.set_timer))
                    setItems(it) { _, position ->
                        viewModel.setLastSetTime(it[position])
                        viewModel.getRemainingTime().value = it[position]
                    }
                }
                alertDialog.show()
            }
        }
    }
}