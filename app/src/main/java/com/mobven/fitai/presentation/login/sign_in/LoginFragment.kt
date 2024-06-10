package com.mobven.fitai.presentation.login.sign_in


import androidx.fragment.app.viewModels
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