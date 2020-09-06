package com.example.updatedtrainingapp.fragments

import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

open class BaseFragment(private val layoutID: Int) : Fragment(layoutID) {

    private val parentJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + parentJob)

    fun cancelCoroutines() {
        parentJob.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        cancelCoroutines()
    }
}