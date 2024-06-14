package com.mobven.fitai.presentation.add.training.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobven.fitai.R
import com.mobven.fitai.databinding.CardAiPoweredTrainingBinding

class TrainingAdapter(
    private val onTrainingItemClicked: (TrainingModel) -> Unit
) :
    ListAdapter<TrainingModel, TrainingAdapter.TrainingViewHolder>(TrainingDiffUtil()) {

    inner class TrainingViewHolder(val binding: CardAiPoweredTrainingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(trainingModel: TrainingModel) {
            with(binding) {
                addItemImage.setImageResource(trainingModel.image!!)
                tvSelectedAddItemsMinute.text =
                    root.context.getString(R.string.training_card_minute, trainingModel.minute)
                tvSelectedAddItemsTitle.text = trainingModel.name
                tvSelectedAddItemsDescription.text = trainingModel.description

                imageAiPoweredMinus.setOnClickListener {
                    onTrainingItemClicked(trainingModel)
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CardAiPoweredTrainingBinding.inflate(layoutInflater, parent, false)
        return TrainingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}