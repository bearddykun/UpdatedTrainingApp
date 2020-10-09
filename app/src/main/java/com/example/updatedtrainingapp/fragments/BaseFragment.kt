package com.example.updatedtrainingapp.fragments

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

open class BaseFragment(layoutID: Int) : Fragment(layoutID) {

    fun onBackClick(func: () -> Unit) {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                   func()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }
}


