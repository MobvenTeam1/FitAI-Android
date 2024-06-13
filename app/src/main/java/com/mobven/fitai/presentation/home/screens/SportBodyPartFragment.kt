package com.mobven.fitai.presentation.home.screens

import androidx.fragment.app.activityViewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.mobven.fitai.R
import com.mobven.fitai.common.SharedPreferencesHelper
import com.mobven.fitai.databinding.FragmentSportBodyPartBinding
import com.mobven.fitai.presentation.base.BaseFragment
import com.mobven.fitai.presentation.home.training.viewmodel.TrainingAction
import com.mobven.fitai.presentation.home.training.viewmodel.TrainingViewModel
import com.mobven.fitai.presentation.login.sign_up.adapter.SignUpListAdapter
import com.mobven.fitai.util.enums.TrainingSelectorItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SportBodyPartFragment :
    BaseFragment<FragmentSportBodyPartBinding>(FragmentSportBodyPartBinding::inflate) {

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
        val bodyPartList = trainingViewModel.trainingSelectorItem

        adapter.submitList(bodyPartList)
        binding.rvBodyPartSport.adapter = adapter

        binding.btnBodyPartContinue.setOnClickListener {
            bodyPartList.forEach {
                if (it.isSelected) {
                    trainingViewModel.workoutDetails.focusAreas =
                        trainingViewModel.workoutDetails.focusAreas +
                                it.title +
                                getString(R.string.comma)
                }
            }

            val userAuthKey = SharedPreferencesHelper.getUserAuthKey(requireActivity()) ?: ""

            trainingViewModel.onAction(TrainingAction.SaveWorkoutDetails(userAuthKey))

            val navOptions =
                NavOptions.Builder()
                    .setPopUpTo(R.id.trainingFragment, true)
                    .build()

            findNavController().navigate(
                R.id.action_trainingFragment_to_planCreatingFragment,
                null,
                navOptions
            )
        }
    }

    private fun handleError(error: String) {
        println(error)
    }

    private fun handleLoading() {
        println(getString(R.string.loading))
    }

    override fun callInitialViewModelFunction() {
        trainingViewModel.onAction(TrainingAction.GetTrainingSelectorItem(TrainingSelectorItem.SPORT_BODY))
    }
}