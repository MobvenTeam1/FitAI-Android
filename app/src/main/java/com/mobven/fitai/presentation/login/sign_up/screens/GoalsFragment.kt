package com.mobven.fitai.presentation.login.sign_up.screens

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.mobven.fitai.MainActivity
import com.mobven.fitai.R
import com.mobven.fitai.common.SharedPreferencesHelper
import com.mobven.fitai.databinding.FragmentGoalsBinding
import com.mobven.fitai.presentation.base.BaseFragment
import com.mobven.fitai.presentation.login.sign_up.adapter.SignUpListAdapter
import com.mobven.fitai.presentation.login.sign_up.model.ListSelectorItem
import com.mobven.fitai.presentation.login.sign_up.viewmodel.SignUpAction
import com.mobven.fitai.presentation.login.sign_up.viewmodel.SignUpViewModel
import com.mobven.fitai.util.enums.SignUpFragmentType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GoalsFragment : BaseFragment<FragmentGoalsBinding>(FragmentGoalsBinding::inflate) {
    private val adapter = SignUpListAdapter()
    private val signUpViewModel: SignUpViewModel by activityViewModels()

    override fun observeUi() {
        signUpViewModel.signUpState.observe(viewLifecycleOwner) { signUpState ->
            when {
                signUpState.isError -> {
                    handleError(signUpState.errorMessage)
                }

                signUpState.isLoading -> {
                    handleLoading()
                }

                else -> {
                    handleSuccess(signUpState.signUpSelectorList)
                }
            }
        }
    }

    private fun handleSuccess(goalList: List<ListSelectorItem>) {
        with(binding) {
            adapter.submitList(goalList)
            rvGoals.adapter = adapter

            btnGoalsContinue.setOnClickListener {
                goalList.forEach {
                    if (it.isSelected) {
                        signUpViewModel.onAction(SignUpAction.EnterProfileGoals(it.title))

                        signUpViewModel.onAction(
                            SignUpAction.EnterFirstLogin(
                                SharedPreferencesHelper.getUserAuthKey(requireActivity())
                                    ?: getString(R.string.empty_string)
                            )
                        )

                        Log.e(
                            "GoalsFragment",
                            "User auth key: ${
                                SharedPreferencesHelper.getUserAuthKey(
                                    requireActivity()
                                )
                            }"
                        )

                        val intent = Intent(requireActivity(), MainActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.please_enter_your_goals),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun handleError(error: String) {
        println(error)
    }

    private fun handleLoading() {
        println(getString(R.string.loading))
    }

    override fun callInitialViewModelFunction() {
        signUpViewModel.onAction(SignUpAction.GetSelectorItem(SignUpFragmentType.GOALS))
    }
}
