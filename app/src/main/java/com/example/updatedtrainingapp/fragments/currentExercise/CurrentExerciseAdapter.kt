package com.example.updatedtrainingapp.fragments.currentExercise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.updatedtrainingapp.R

class CurrentExerciseAdapter :
    RecyclerView.Adapter<CurrentExerciseAdapter.CurrentExerciseViewHolder>() {

    private var list: MutableList<String>? = null

    class CurrentExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val trainingListText: TextView = itemView.findViewById(R.id.trainingListText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentExerciseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.training_list_item, parent, false)
        return CurrentExerciseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: CurrentExerciseViewHolder, position: Int) {
        list?.get(position).let { holder.trainingListText.text = it }
    }

    fun swapAdapter(list: MutableList<String>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun updateList(set: String) {
        if (list == null) {
            list = mutableListOf()
        }
        list?.add(set)
        notifyDataSetChanged()
    }
}