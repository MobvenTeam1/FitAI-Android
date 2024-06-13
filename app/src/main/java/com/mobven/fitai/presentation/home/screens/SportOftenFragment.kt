package com.mobven.fitai.presentation.home.screens

import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.mobven.fitai.R
import com.mobven.fitai.databinding.FragmentSportOftenBinding
import com.mobven.fitai.presentation.base.BaseFragment
import com.mobven.fitai.presentation.home.training.viewmodel.TrainingAction
import com.mobven.fitai.presentation.home.training.viewmodel.TrainingViewModel
import com.mobven.fitai.presentation.login.sign_up.adapter.SignUpListAdapter
import com.mobven.fitai.presentation.login.sign_up.model.ListSelectorItem
import com.mobven.fitai.util.enums.TrainingSelectorItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SportOftenFragment :
    BaseFragment<FragmentSportOftenBinding>(FragmentSportOftenBinding::inflate) {

    private val adapter = SignUpListAdapter()
    private val trainingViewModel: TrainingViewModel by activityViewModels()

    override fun observeUi() {
        trainingViewModel.trainingUiState.observe(viewLifecycleOwner) { trainingState ->
            when {
                trainingState.isError -> {
                    handleError(trainingState.errorMessage)
                }

                trainingState.isLoading -> {
                    handleLoading()
                }

                else -> {
                    handleSuccess()
                }

            }
        }
    }

    private fun handleSuccess() {

        val sportOftenList = trainingViewModel.trainingSelectorItem

        adapter.submitList(sportOftenList)
        binding.rvSportsOften.adapter = adapter

        binding.btnSportsOftenContinue.setOnClickListener {
            sportOftenList.forEach {
                if (it.isSelected) {
                    trainingViewModel.workoutDetails.workoutFrequency = it.title
                }
            }
            val currentItem =
                requireActivity().findViewById<ViewPager2>(R.id.vp_training).currentItem
            val nextItem = currentItem + 1
            requireActivity().findViewById<ViewPager2>(R.id.vp_training)
                .setCurrentItem(nextItem, true)
        }
    }

    private fun handleError(error: String) {
        println(error)
    }

    private fun handleLoading() {
        println(getString(R.string.loading))
    }

    override fun callInitialViewModelFunction() {
        trainingViewModel.onAction(TrainingAction.GetTrainingSelectorItem(TrainingSelectorItem.SPORT_OFTEN))
    }
}