package com.mobven.fitai.presentation.login.sign_up.screens

import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.mobven.fitai.R
import com.mobven.fitai.databinding.FragmentHeightSignUpBinding
import com.mobven.fitai.presentation.base.BaseFragment
import com.mobven.fitai.presentation.login.sign_up.viewmodel.SignUpAction
import com.mobven.fitai.presentation.login.sign_up.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeightSignUpFragment :
    BaseFragment<FragmentHeightSignUpBinding>(FragmentHeightSignUpBinding::inflate) {

    private val viewModel: SignUpViewModel by activityViewModels()
    override fun observeUi() {
        requireActivity().findViewById<ImageView>(R.id.toolbar_back).visibility =
            ProgressBar.VISIBLE

        binding.btnHeightContinue.setOnClickListener {

            if (binding.etHeight.text.toString().isEmpty()) {
                binding.etHeight.error = getString(R.string.please_enter_your_height)
                return@setOnClickListener
            } else {
                viewModel.onAction(
                    SignUpAction.EnterHeight(
                        binding.etHeight.text.toString().toInt()
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