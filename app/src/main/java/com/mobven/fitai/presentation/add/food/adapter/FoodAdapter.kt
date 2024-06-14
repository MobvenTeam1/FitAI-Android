package com.mobven.fitai.presentation.add.food.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobven.fitai.R
import com.mobven.fitai.databinding.CardAiPoweredFoodBinding

class FoodAdapter(
    private val onFoodCountDecreased: (FoodModel) -> Unit,
) : ListAdapter<FoodModel, FoodAdapter.FoodViewHolder>(FoodDiffUtil()) {

    inner class FoodViewHolder(val binding: CardAiPoweredFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(foodModel: FoodModel) {
            with(binding) {
                ivAiPoweredFood.setImageResource(foodModel.image!!)
                tvAiPoweredCount.text =
                    root.context.getString(R.string.x_how_many_food, foodModel.count)

                if (foodModel.count >= 2) {
                    tvAiPoweredCount.visibility = View.VISIBLE
                }

                imageAiPoweredMinus.setOnClickListener {
                    onFoodCountDecreased(foodModel)
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CardAiPoweredFoodBinding.inflate(layoutInflater, parent, false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}