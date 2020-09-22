package com.example.updatedtrainingapp.fragments.training

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.dataBase.objects.ExerciseObject
import org.jetbrains.anko.find

class TrainingAdapter : RecyclerView.Adapter<TrainingAdapter.ThisTrainingViewHolder>() {

    private var list: List<ExerciseObject>? = null

    private var listener: OnTrainingItemClickListener? = null
    private var longListener: OnTrainingItemLongClickListener? = null

    interface OnTrainingItemClickListener {
        fun onTrainingItemClick(exerciseName: String)
    }

    interface OnTrainingItemLongClickListener {
        fun onTrainingItemLongClick(exerciseName: String)
    }

    fun setOnTrainingItemClickListener(listener: OnTrainingItemClickListener) {
        this.listener = listener
    }

    fun setOnTrainingItemLongClickListener(listener: OnTrainingItemLongClickListener) {
        this.longListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ThisTrainingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.exercise_item, parent, false)

        return ThisTrainingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: ThisTrainingViewHolder, position: Int) {
        list?.let { list ->
            if (list[position].exerciseName.isEmpty() || list[position].exerciseImage.isEmpty()) return

            holder.itemView.setOnClickListener {
                listener?.onTrainingItemClick(
                    list[position].exerciseName
                )
            }
            holder.itemView.setOnLongClickListener {
                longListener?.onTrainingItemLongClick(list[position].exerciseName)
                true
            }
            holder.text.text = list[position].exerciseName
            holder.image.setImageResource(list[position].exerciseImage.toInt())
        }
    }

    fun swapAdapter(list: List<ExerciseObject>) {
        this.list = list
        notifyDataSetChanged()
    }

    class ThisTrainingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text = itemView.find<TextView>(R.id.exerciseText)
        val image = itemView.find<ImageView>(R.id.exerciseImage)
    }
}