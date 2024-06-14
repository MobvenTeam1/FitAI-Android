package com.mobven.fitai.presentation.home.suggest

import androidx.recyclerview.widget.DiffUtil

class SuggestDiffUtil : DiffUtil.ItemCallback<SuggestModel>() {
    override fun areItemsTheSame(oldItem: SuggestModel, newItem: SuggestModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: SuggestModel, newItem: SuggestModel): Boolean {
        return oldItem == newItem
    }
}