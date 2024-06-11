package com.mobven.fitai.presentation.login.sign_in


import android.content.Intent
import android.util.Log
import androidx.fragment.app.viewModels
import com.mobven.fitai.MainActivity
import com.mobven.fitai.R
import com.mobven.fitai.common.SharedPreferencesHelper
import com.mobven.fitai.data.model.dto.SignInDto
import com.mobven.fitai.databinding.FragmentLoginBinding
import com.mobven.fitai.presentation.base.BaseFragment
import com.mobven.fitai.presentation.login.sign_in.viewmodel.SignInAction
import com.mobven.fitai.presentation.login.sign_in.viewmodel.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: SignInViewModel by viewModels()

    override fun observeUi() {
        with(binding) {
            toolbarLogin.toolbarBack.setOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }

            loginButton.setOnClickListener {
                val editTextEmail = editTextEmailLogin.text.toString()
                loginEmail.helperText = viewModel.validEmail(editTextEmail)

                val editTextPassword = editTextPasswordLogin.text.toString()
                loginPassword.helperText = viewModel.validPassword(editTextPassword)

                if (allFieldsValid()) {
                    viewModel.onAction(
                        SignInAction.LoginUser(
                            SignInDto(
                                editTextEmailLogin.text.toString(),
                                editTextPasswordLogin.text.toString()
                            )
                        )
                    )

                    viewModel.signInUiState.observe(viewLifecycleOwner) {
                        when {
                            it.isLoading -> {
                                Log.e(getString(R.string.login), getString(R.string.loading))
                            }

                            it.isError -> {
                                Log.e(getString(R.string.login),
                                    getString(R.string.error, it.errorMessage))
                            }

                            else -> {
                                SharedPreferencesHelper.saveUserAuthKey(
                                    requireActivity(),
                                    it.userAuthKey
                                )
                                val intent = Intent(requireActivity(), MainActivity::class.java)
                                startActivity(intent)
                                requireActivity().finish()

                                Log.e(getString(R.string.login),
                                    getString(R.string.success, it.userAuthKey))
                            }
                        }
                    }
                }
            }

            tvRegister.setOnClickListener {
                val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                navigate(action)
            }

            loginForgotPassword.setOnClickListener {
                val action =
                    LoginFragmentDirections.actionLoginFragmentToResetPasswordEmailFragment()
                navigate(action)
            }
        }
    }

    private fun allFieldsValid(): Boolean {
        return binding.loginEmail.helperText == null &&
                binding.loginPassword.helperText == null
    }
}