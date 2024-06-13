package com.mobven.fitai.presentation.home.personal_plan

import androidx.recyclerview.widget.DiffUtil

class PersonalPlanDiffUtil : DiffUtil.ItemCallback<PersonalPlanModel>() {
    override fun areItemsTheSame(oldItem: PersonalPlanModel, newItem: PersonalPlanModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PersonalPlanModel, newItem: PersonalPlanModel): Boolean {
        return oldItem == newItem
    }
}