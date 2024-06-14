package com.mobven.fitai.data.mapper

import com.mobven.fitai.presentation.home.personal_plan.PersonalPlanModel
import com.mobven.fitai.presentation.home.suggest.SuggestModel
import com.mobven.fitai.util.enums.CategoryType

fun PersonalPlanModel.toSuggestModel() : SuggestModel {
    return SuggestModel(
        image = this.image,
        name = this.name,
        detail = this.detail
    )
}

fun SuggestModel.toPersonalPlanModel(): PersonalPlanModel {
    return PersonalPlanModel(
        image = this.image,
        name = this.name,
        detail = this.detail,
        personalPlanType = CategoryType.NUTRITION
    )
}