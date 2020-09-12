package com.example.updatedtrainingapp.fragments.currentExercise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.updatedtrainingapp.R
import org.jetbrains.anko.find

class CurrentExerciseAdapter :
    RecyclerView.Adapter<CurrentExerciseAdapter.CurrentExerciseViewHolder>() {

    private var list: MutableList<String>? = null

    class CurrentExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val trainingListText = itemView.find<TextView>(R.id.trainingListText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentExerciseViewHolder {
        return CurrentExerciseViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.exercise_choice_item, parent, false)
        )
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
        list = mutableListOf()
        list?.add(set)
        notifyDataSetChanged()
    }
}