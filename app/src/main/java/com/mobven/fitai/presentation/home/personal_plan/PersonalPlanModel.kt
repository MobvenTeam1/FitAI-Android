package com.mobven.fitai.presentation.home.personal_plan

import com.mobven.fitai.util.enums.CategoryType

data class PersonalPlanModel(
    var image : Int,
    var name : String,
    var detail : String,
    val personalPlanType: CategoryType
)
