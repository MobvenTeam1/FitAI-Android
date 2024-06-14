package com.mobven.fitai.presentation.home.screens

import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.mobven.fitai.R
import com.mobven.fitai.databinding.FragmentHealthProblemBinding
import com.mobven.fitai.presentation.base.BaseFragment
import com.mobven.fitai.presentation.home.training.viewmodel.TrainingViewModel

class HealthProblemFragment :
    BaseFragment<FragmentHealthProblemBinding>(FragmentHealthProblemBinding::inflate) {

    private val trainingViewModel: TrainingViewModel by activityViewModels()

    override fun observeUi() {
        binding.btnHealthProblemContinue.setOnClickListener {
            trainingViewModel.workoutDetails.healthProblem = binding.editTextHealthProblem.text.toString()

            val currentItem =
                requireActivity().findViewById<ViewPager2>(R.id.vp_training).currentItem
            val nextItem = currentItem + 1
            requireActivity().findViewById<ViewPager2>(R.id.vp_training)
                .setCurrentItem(nextItem, true)
        }
    }
}