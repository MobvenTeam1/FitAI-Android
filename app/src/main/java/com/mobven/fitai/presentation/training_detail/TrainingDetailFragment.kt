package com.mobven.fitai.presentation.training_detail

import androidx.navigation.fragment.findNavController
import com.mobven.fitai.R
import com.mobven.fitai.databinding.FragmentTrainingDetailBinding
import com.mobven.fitai.presentation.base.BaseFragment

class TrainingDetailFragment : BaseFragment<FragmentTrainingDetailBinding>(FragmentTrainingDetailBinding::inflate){

    override fun observeUi() {
        super.observeUi()

        with(binding){
            btnStartTraining.setOnClickListener {
                findNavController()
                    .navigate(R.id.action_trainingDetailFragment_to_startTrainingFragment)
            }
        }
    }

}