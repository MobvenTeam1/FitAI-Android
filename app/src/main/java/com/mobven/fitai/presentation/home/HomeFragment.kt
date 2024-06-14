package com.mobven.fitai.presentation.home

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.mobven.fitai.R
import com.mobven.fitai.common.SharedPreferencesHelper
import com.mobven.fitai.data.mapper.toPersonalPlanModelList
import com.mobven.fitai.databinding.FragmentHomeBinding
import com.mobven.fitai.presentation.base.BaseFragment
import com.mobven.fitai.presentation.home.adapter.CategoryItem
import com.mobven.fitai.presentation.home.adapter.HomeCategoryAdapter
import com.mobven.fitai.presentation.home.calendar.CalendarItem
import com.mobven.fitai.presentation.home.calendar.HomeCalendarAdapter
import com.mobven.fitai.presentation.home.personal_plan.PersonalPlanAdapter
import com.mobven.fitai.presentation.home.personal_plan.PersonalPlanModel
import com.mobven.fitai.presentation.home.viewmodel.HomeAction
import com.mobven.fitai.presentation.home.viewmodel.HomeViewModel
import com.mobven.fitai.util.LoadingDialogHelper
import dagger.hilt.android.AndroidEntryPoint

typealias HDirections = HomeFragmentDirections

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val calendarAdapter = HomeCalendarAdapter()
    private val trainingAdapter = HomeCategoryAdapter()
    private val foodAdapter = HomeCategoryAdapter()

    private val trainingPlanAdapter = PersonalPlanAdapter()
    private val foodPlanAdapter = PersonalPlanAdapter()

    private val homeViewModel: HomeViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun observeUi() {
        homeViewModel.homeUiState.observe(viewLifecycleOwner) { homeState ->
            when {
                homeState.isError -> {
                    handleError(homeState.errorMessage)
                }

                homeState.isLoading -> {
                    handleLoading()
                }

                else -> {
                    LoadingDialogHelper.dismissLoadingDialog()
                    handleSuccess(
                        trainingList = homeState.trainingCategoryList,
                        foodList = homeState.foodCategoryList,
                        dateList = homeViewModel.dateList,
                        foodPlanList = homeState.foodPlanList
                    )
                }
            }
        }


        binding.tvHomeDailyGoals.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToBottomSheetFragment()
            navigate(action)
        }

    }


    override fun navigate(action: NavDirections) {
        findNavController().navigate(action)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun handleSuccess(
        trainingList: List<CategoryItem>,
        foodList: List<CategoryItem>,
        dateList: List<CalendarItem>,
        foodPlanList: List<PersonalPlanModel>
    ) {
        with(binding) {

            if (SharedPreferencesHelper.getNutritionPlan(requireContext())) {
                includeHomeCreateNutrition.cardHomePersonalized.visibility = View.GONE
                includeHomePersonalizedNutrition.planCardView.visibility = View.VISIBLE
            } else {
                includeHomeCreateNutrition.cardHomePersonalized.visibility = View.VISIBLE
                includeHomePersonalizedNutrition.planCardView.visibility = View.GONE
            }

            if (SharedPreferencesHelper.getExercisePlan(requireContext())) {
                includeHomeCreateExercise.cardHomePersonalized.visibility = View.GONE
                includeHomePersonalizedTraining.planCardView.visibility = View.VISIBLE
            } else {
                includeHomeCreateExercise.cardHomePersonalized.visibility = View.VISIBLE
                includeHomePersonalizedTraining.planCardView.visibility = View.GONE
            }

            with(includeHomeIntakeCalorie) {
                ivCalorieCardIcon.setImageResource(
                    R.drawable.intake_calorie,
                )
                tvCalorieCardTitle.text = getString(R.string.intake_calorie)
                this.tvCalorieCardValue.text = getString(R.string._900_kcal)
            }

            with(includeHomeBurnedCalorie) {
                ivCalorieCardIcon.setImageResource(
                    R.drawable.expend_calorie,
                )
                tvCalorieCardTitle.text = getString(R.string.burned_calorie)
                this.tvCalorieCardValue.text = getString(R.string._1300_kcal)
            }

            with(includeHomeRemainingCalorie) {
                ivCalorieCardIcon.setImageResource(
                    R.drawable.calorie_goal,
                )
                tvCalorieCardTitle.text = getString(R.string.daily_goal)
                this.tvCalorieCardValue.text = getString(R.string._2500_kcal)
            }

            with(includeHomeCreateNutrition) {
                ivPersonalizedCardIcon.setImageResource(
                    R.drawable.pan,
                )
                tvCreatePersonalized.text = getString(R.string.create_personalized_food)
                cardHomePersonalized.setOnClickListener {
                    findNavController().navigate(R.id.action_homeFragment_to_nutritionFragment)
                }
            }

            with(includeHomeCreateExercise) {
                ivPersonalizedCardIcon.setImageResource(
                    R.drawable.dumbell,
                )
                tvCreatePersonalized.text = getString(R.string.create_personalized_training)
                cardHomePersonalized.setOnClickListener {
                    findNavController().navigate(R.id.action_homeFragment_to_trainingFragment)
                }
            }

            with(includeHomePersonalizedNutrition) {
                cardViewImage.setImageResource(
                    R.drawable.breakfast_category,
                )
                tvCardHeader.text = getString(R.string.breakfast)
                tvTime.text = getString(R.string._15_minutes)
                tvKcal.text = getString(R.string._550_kcal)
                cardViewImage.setOnClickListener {
                    llPlanCardDetail.visibility =
                        if (llPlanCardDetail.visibility == View.VISIBLE)
                            View.GONE
                        else
                            View.VISIBLE
                }
                foodAdapter.submitList(foodList)
                planRecyclerView.adapter = foodPlanAdapter
            }

            with(includeHomePersonalizedTraining) {
                cardViewImage.setImageResource(
                    R.drawable.pilates_woman,
                )
                tvCardHeader.text = getString(R.string.pilates)
                tvTime.text = getString(R.string._15_minutes)
                tvKcal.text = getString(R.string._250_kcal)
                cardViewImage.setOnClickListener {
                    llPlanCardDetail.visibility =
                        if (llPlanCardDetail.visibility == View.VISIBLE)
                            View.GONE
                        else
                            View.VISIBLE
                }
                with(homeViewModel) {
                    trainingPlanAdapter.submitList(
                        workoutModelList.workoutList.toPersonalPlanModelList()
                    )
                }
            }

            ivHomeProfile.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
            }

            ebtnHomeAddExercises.setOnClickListener {
                navigate(HDirections.actionHomeFragmentToAddTrainingFragment())
            }

            ebtnHomeAddFood.setOnClickListener {
                navigate(HDirections.actionHomeFragmentToAddFoodFragment())
            }

            calendarAdapter.submitList(dateList)
            trainingAdapter.submitList(trainingList)
            foodPlanAdapter.submitList(foodPlanList)

            rvHomeCalendar.adapter = calendarAdapter
            rvHomeTrainingCategory.adapter = trainingAdapter
            rvHomeFoodCategory.adapter = foodAdapter
        }
    }

    private fun handleError(error: String) {
        LoadingDialogHelper.dismissLoadingDialog()
        println(error)
    }

    private fun handleLoading() {
        LoadingDialogHelper.showLoadingDialog(requireActivity())
        println(getString(R.string.loading))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun callInitialViewModelFunction() {
        homeViewModel.onAction(HomeAction.GetCategoryItem)
        homeViewModel.onAction(HomeAction.GetCalendarItem)
        homeViewModel.onAction(HomeAction.GetBreakfastItems)
    }
}
