package com.mobven.fitai.presentation.login.sign_up.screens

import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.mobven.fitai.R
import com.mobven.fitai.databinding.FragmentGenderSignUpBinding
import com.mobven.fitai.presentation.base.BaseFragment
import com.mobven.fitai.presentation.login.sign_up.adapter.SignUpListAdapter
import com.mobven.fitai.presentation.login.sign_up.viewmodel.SignUpAction
import com.mobven.fitai.presentation.login.sign_up.viewmodel.SignUpViewModel
import com.mobven.fitai.util.LoadingDialogHelper
import com.mobven.fitai.util.enums.SignUpFragmentType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenderSignUpFragment :
    BaseFragment<FragmentGenderSignUpBinding>(FragmentGenderSignUpBinding::inflate) {
    private val adapter = SignUpListAdapter()

    private val viewModel: SignUpViewModel by activityViewModels()

    override fun observeUi() {
        viewModel.signUpState.observe(viewLifecycleOwner) { signUpState ->
            when {
                signUpState.isError -> {
                    LoadingDialogHelper.dismissLoadingDialog()
                    handleError(signUpState.errorMessage)
                }

                signUpState.isLoading -> {
                    LoadingDialogHelper.showLoadingDialog(requireActivity())
                    handleLoading()
                }

                else -> {
                    LoadingDialogHelper.dismissLoadingDialog()
                    handleSuccess()
                }
            }
        }
    }

    private fun handleSuccess() {

        val genderList = viewModel.signUpSelectorList

        adapter.submitList(genderList)
        binding.rvGender.adapter = adapter

        binding.btnGenderContinue.setOnClickListener {
            genderList.forEach {
                if (it.isSelected) {
                    viewModel.onAction(SignUpAction.EnterGender(it.title))

                    val currentItem =
                        requireActivity().findViewById<ViewPager2>(R.id.sign_up_view_pager).currentItem
                    val nextItem = currentItem + 1
                    requireActivity().findViewById<ViewPager2>(R.id.sign_up_view_pager)
                        .setCurrentItem(nextItem, true)

                    requireActivity().findViewById<ImageView>(R.id.toolbar_back).visibility = ProgressBar.VISIBLE
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
        viewModel.onAction(SignUpAction.GetSelectorItem(SignUpFragmentType.GENDER))
        requireActivity().findViewById<ImageView>(R.id.toolbar_back).visibility = ProgressBar.INVISIBLE
    }
}
