package com.example.updatedtrainingapp.fragments.trainingsChoice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.updatedtrainingapp.R

class TrainingsAdapter : RecyclerView.Adapter<TrainingsAdapter.TrainingViewHolder>() {

    private var listener: OnTrainingItemListener? = null
    private var listenerLong: OnTrainingItemLongListener? = null
    private var trainingList: List<String>? = null

    interface OnTrainingItemListener {
        fun onTrainingListItemClick(name: String)
    }

    interface OnTrainingItemLongListener {
        fun onTrainingListItemLongClick(name: String)
    }

    fun setOnTrainingItemListener(listener: OnTrainingItemListener) {
        this.listener = listener
    }

    fun setOnTrainingItemLongListener(listener: OnTrainingItemLongListener) {
        this.listenerLong = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): TrainingViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.training_list_item, parent, false)

        return TrainingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return trainingList?.size ?: 0
    }

    fun swapAdapter(list: List<String>) {
        trainingList = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        if (trainingList != null) {
            trainingList?.let { trainingList ->
                holder.itemView.setOnClickListener {
                    listener?.onTrainingListItemClick(trainingList[holder.adapterPosition])
                }
                holder.itemView.setOnLongClickListener {
                    listenerLong?.onTrainingListItemLongClick(trainingList[holder.adapterPosition])
                    true
                }
                holder.text.text = trainingList[position]
            }
        }
    }

    class TrainingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.trainingListText)
    }
}