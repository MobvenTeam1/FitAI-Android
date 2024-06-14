package com.mobven.fitai.presentation.home.suggest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobven.fitai.R
import com.mobven.fitai.databinding.CardAiSuggestionsBinding

class SuggestAdapter(

) : ListAdapter<SuggestModel, SuggestAdapter.SuggestViewHolder>(SuggestDiffUtil()) {

    inner class SuggestViewHolder(val binding: CardAiSuggestionsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(suggestModel: SuggestModel) {
            with(binding) {

                imageAiPreferredItems.setImageResource(suggestModel.image)
                tvCardAiSuggestionsTitle.text = suggestModel.name
                tvCardAiSuggestionsDescription.text = suggestModel.detail

                if (suggestModel.isSelected) {
                    cardAiSuggestions.strokeColor = root.context.getColor(R.color.green_500)
                } else {
                    cardAiSuggestions.strokeColor = root.context.getColor(R.color.transparent)
                }

                cardAiSuggestions.setOnClickListener {
                    currentList.forEach { it.isSelected = false }

                    suggestModel.isSelected = !suggestModel.isSelected

                    notifyDataSetChanged()
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CardAiSuggestionsBinding.inflate(layoutInflater, parent, false)
        return SuggestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SuggestViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}