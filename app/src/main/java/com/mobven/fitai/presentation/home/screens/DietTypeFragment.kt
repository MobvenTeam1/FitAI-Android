package com.mobven.fitai.presentation.home.screens

import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.mobven.fitai.R
import com.mobven.fitai.common.SharedPreferencesHelper
import com.mobven.fitai.databinding.FragmentDietTypeBinding
import com.mobven.fitai.presentation.base.BaseFragment
import com.mobven.fitai.presentation.home.viewmodel.HomeAction
import com.mobven.fitai.presentation.home.viewmodel.HomeViewModel
import com.mobven.fitai.presentation.login.sign_up.adapter.SignUpListAdapter
import com.mobven.fitai.presentation.login.sign_up.model.ListSelectorItem
import com.mobven.fitai.util.enums.HomeFragmentType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DietTypeFragment : BaseFragment<FragmentDietTypeBinding>(FragmentDietTypeBinding::inflate) {
    private val adapter = SignUpListAdapter()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun observeUi() {
        homeViewModel.homeUiState.observe(viewLifecycleOwner) { homeState ->
            when {
                homeState.isError -> {
                    handleError(homeState.errorMessage)
                }

                homeState.isLoading -> {
                    handleLoading()
                }

                else -> {
                    handleSuccess(homeState.signUpSelectorList)
                }
            }
        }
    }

    private fun handleSuccess(dietTypeList: List<ListSelectorItem>) {
        adapter.submitList(dietTypeList)
        binding.rvDietType.adapter = adapter

        binding.btnDietTypeContinue.setOnClickListener {
            SharedPreferencesHelper.saveNutritionPlan(
                requireActivity(),
                true
            )

            val navOptions =
                NavOptions.Builder()
                    .setPopUpTo(R.id.nutritionFragment, true)
                    .build()
            findNavController().navigate(
                R.id.action_nutritionFragment_to_planCreatingFragment,
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
        homeViewModel.onAction(HomeAction.GetSelectorItem(HomeFragmentType.DIET))
    }
}
