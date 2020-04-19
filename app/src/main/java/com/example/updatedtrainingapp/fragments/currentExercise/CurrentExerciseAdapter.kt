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

    private var list: List<String>? = null

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
        list?.let { return it.size }
        return 0
    }

    override fun onBindViewHolder(holder: CurrentExerciseViewHolder, position: Int) {
         list?.get(position).let { holder.trainingListText.text = it }
    }

    fun swapAdapter(list: List<String>) {
        this.list = list
        notifyDataSetChanged()
    }
}