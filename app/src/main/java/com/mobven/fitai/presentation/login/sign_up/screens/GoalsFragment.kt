package com.mobven.fitai.presentation.login.sign_up.screens

import android.content.Intent
import android.util.Log
import androidx.fragment.app.activityViewModels
import com.mobven.fitai.MainActivity
import com.mobven.fitai.R
import com.mobven.fitai.common.SharedPreferencesHelper
import com.mobven.fitai.databinding.FragmentGoalsBinding
import com.mobven.fitai.presentation.base.BaseFragment
import com.mobven.fitai.presentation.login.sign_up.adapter.SignUpListAdapter
import com.mobven.fitai.presentation.login.sign_up.viewmodel.SignUpAction
import com.mobven.fitai.presentation.login.sign_up.viewmodel.SignUpViewModel
import com.mobven.fitai.util.LoadingDialogHelper
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
                    handleSuccess()
                }
            }
        }
    }

    private fun handleSuccess() {
        LoadingDialogHelper.dismissLoadingDialog()
        with(binding) {

            val goalList = signUpViewModel.signUpSelectorList

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
                            getString(R.string.goalsfragment),
                            getString(
                                R.string.auth_user_key, SharedPreferencesHelper.getUserAuthKey(
                                    requireActivity()
                                )
                            )
                        )

                        val intent = Intent(requireActivity(), MainActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    }
                }
            }
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
    override fun callInitialViewModelFunction() {
        signUpViewModel.onAction(SignUpAction.GetSelectorItem(SignUpFragmentType.GOALS))
    }
}
