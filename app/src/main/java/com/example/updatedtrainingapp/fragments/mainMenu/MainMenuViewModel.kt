package com.example.updatedtrainingapp.fragments.mainMenu

import androidx.lifecycle.ViewModel
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import javax.inject.Inject

class MainMenuViewModel @Inject constructor(): ViewModel() {

    fun getSeries(): LineGraphSeries<DataPoint>? {
        return LineGraphSeries(
            arrayOf(
                DataPoint(0.toDouble(), 1.toDouble()), DataPoint(1.toDouble(), 2.toDouble()),
                DataPoint(2.toDouble(), 0.toDouble())
            )
        )
    }
}
