package com.mobven.fitai.presentation.login.sign_up.screens

import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.mobven.fitai.R
import com.mobven.fitai.databinding.FragmentWeightSignUpBinding
import com.mobven.fitai.presentation.base.BaseFragment
import com.mobven.fitai.presentation.login.sign_up.viewmodel.SignUpAction
import com.mobven.fitai.presentation.login.sign_up.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeightSignUpFragment :
    BaseFragment<FragmentWeightSignUpBinding>(FragmentWeightSignUpBinding::inflate) {

    private val viewModel: SignUpViewModel by activityViewModels()

    override fun observeUi() {
        binding.btnWeightContinue.setOnClickListener {
            if (binding.etWeight.text.toString().isEmpty()) {
                binding.etWeight.error = getString(R.string.please_enter_your_weight)
                return@setOnClickListener
            } else {
                viewModel.onAction(
                    SignUpAction.EnterWeight(
                        binding.etWeight.text.toString().toInt()
                    )
                )
                val currentItem =
                    requireActivity().findViewById<ViewPager2>(R.id.sign_up_view_pager).currentItem
                val nextItem = currentItem + 1
                requireActivity().findViewById<ViewPager2>(R.id.sign_up_view_pager)
                    .setCurrentItem(nextItem, true)
            }
        }
    }
}
