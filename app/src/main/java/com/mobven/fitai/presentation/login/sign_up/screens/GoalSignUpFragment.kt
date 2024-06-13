package com.mobven.fitai.presentation.login.sign_up.screens

import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.mobven.fitai.R
import com.mobven.fitai.databinding.FragmentGoalSignUpBinding
import com.mobven.fitai.presentation.base.BaseFragment
import com.mobven.fitai.presentation.login.sign_up.viewmodel.SignUpAction
import com.mobven.fitai.presentation.login.sign_up.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GoalSignUpFragment :
    BaseFragment<FragmentGoalSignUpBinding>(FragmentGoalSignUpBinding::inflate) {

    private val viewModel: SignUpViewModel by activityViewModels()

    override fun observeUi() {
        with(binding) {
            btnWeightGoalContinue.setOnClickListener {
                if (etWeightGoal.text.toString().isEmpty()) {
                    etWeightGoal.error = getString(R.string.please_enter_your_weight_goal)
                    return@setOnClickListener
                } else {
                    viewModel.onAction(
                        SignUpAction.EnterWeightGoal(
                            etWeightGoal.text.toString().toInt()
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
}
