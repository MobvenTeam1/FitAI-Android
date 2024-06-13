package com.mobven.fitai.presentation.home.personal_plan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobven.fitai.databinding.PlanRecyclerItemBinding

class PersonalPlanAdapter() :
    ListAdapter<PersonalPlanModel, PersonalPlanAdapter.PersonalPlanViewHolder>(PersonalPlanDiffUtil()) {
    inner class PersonalPlanViewHolder(private val binding: PlanRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(personalPlanModel: PersonalPlanModel) {
            with(binding) {
                itemImage.setImageResource(personalPlanModel.image)
                itemName.text = personalPlanModel.name
                itemDetail.text = personalPlanModel.detail
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonalPlanViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PlanRecyclerItemBinding.inflate(layoutInflater, parent, false)
        return PersonalPlanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonalPlanViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}