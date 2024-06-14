package com.mobven.fitai.presentation.add.food.adapter

import androidx.recyclerview.widget.DiffUtil

class FoodDiffUtil : DiffUtil.ItemCallback<FoodModel>() {
    override fun areItemsTheSame(oldItem: FoodModel, newItem: FoodModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: FoodModel, newItem: FoodModel): Boolean {
        return oldItem == newItem
    }
}
