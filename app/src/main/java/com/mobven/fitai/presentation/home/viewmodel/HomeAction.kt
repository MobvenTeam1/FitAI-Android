package com.mobven.fitai.presentation.home.viewmodel

import com.mobven.fitai.data.model.entity.WorkoutEntity
import com.mobven.fitai.util.enums.HomeFragmentType

sealed class HomeAction {
    data class GetSelectorItem(val fragmentType: HomeFragmentType) : HomeAction()
    data object GetCategoryItem : HomeAction()
    data object GetCalendarItem : HomeAction()
    data class GenerateWorkoutPlan(val authToken: String) : HomeAction()
    data object GetWorkoutList : HomeAction()
    data object GetBreakfastItems : HomeAction()
    data object GetSuggestItems : HomeAction()
}
