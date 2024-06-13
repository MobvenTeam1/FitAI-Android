package com.mobven.fitai.presentation.login.sign_in

import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.mobven.fitai.R
import com.mobven.fitai.databinding.FragmentResetPasswordSuccessBinding
import com.mobven.fitai.presentation.base.BaseFragment

class ResetPasswordSuccessFragment : BaseFragment<FragmentResetPasswordSuccessBinding>(FragmentResetPasswordSuccessBinding::inflate){

    override fun observeUi() {
        binding.loginButton.setOnClickListener {
            navigate(ResetPasswordSuccessFragmentDirections.actionResetPasswordSuccessFragmentToLoginFragment())
        }
    }

    override fun navigate(action: NavDirections) {
        val navOptions =
            NavOptions.Builder()
                .setPopUpTo(R.id.resetPasswordSuccessFragment, true)
                .setPopUpTo(R.id.resetPasswordFragment, true)
                .setPopUpTo(R.id.loginFragment, true)
                .build()
        findNavController().navigate(action, navOptions)
    }
}