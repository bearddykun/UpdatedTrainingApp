package com.example.updatedtrainingapp.fragments.trainingDay

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.updatedtrainingapp.R
import org.jetbrains.anko.find

class TrainingDayAdapter : RecyclerView.Adapter<TrainingDayAdapter.ThisTrainingViewHolder>() {

    private var list: MutableList<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ThisTrainingViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.this_training_item, parent, false)

        return ThisTrainingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: ThisTrainingViewHolder, position: Int) {
        if (list != null)
            holder.text.text = list!![position]
    }

    fun swapAdapter(list: MutableList<String>) {
        this.list = list
        notifyDataSetChanged()
    }

    class ThisTrainingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text = itemView.find<TextView>(R.id.thisTrainingExerciseTextView)
    }
}