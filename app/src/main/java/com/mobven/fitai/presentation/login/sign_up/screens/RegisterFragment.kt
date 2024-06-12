package com.mobven.fitai.presentation.login.sign_up.screens

import android.app.AlertDialog
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.mobven.fitai.R
import com.mobven.fitai.common.SharedPreferencesHelper
import com.mobven.fitai.data.model.dto.SignUpDto
import com.mobven.fitai.databinding.FragmentRegisterBinding
import com.mobven.fitai.presentation.base.BaseFragment
import com.mobven.fitai.presentation.login.sign_in.viewmodel.SignInViewModel
import com.mobven.fitai.presentation.login.sign_up.viewmodel.SignUpAction
import com.mobven.fitai.presentation.login.sign_up.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

typealias RDirections = RegisterFragmentDirections

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val signInViewModel: SignInViewModel by viewModels()
    private val signUpViewModel: SignUpViewModel by viewModels()
    private var checkBoxStatus = false

    override fun observeUi() {
        with(binding) {

            signUpTermsCheckbox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    showPrivacyPolicyDialog()
                    checkBoxStatus = true
                }else{
                    checkBoxStatus = false
                }
            }

            btnSignUpContinue.setOnClickListener {

                val editTextEmail = editTextEmailSignUp.text.toString()
                val editTextPassword = editTextPasswordSignUp.text.toString()
                val editTextPasswordAgain = editTextPasswordAgainSignUp.text.toString()
                val editTextUserName = editTextNicknameSignUp.text.toString()

                textInputLayoutEmailSignUp.helperText =
                    signInViewModel.validEmail(editTextEmail)
                textInputLayoutPasswordSignUp.helperText =
                    signInViewModel.validPassword(editTextPassword)
                textInputLayoutPasswordAgainSignUp.helperText =
                    signInViewModel.isSamePassword(editTextPassword, editTextPasswordAgain)
                textInputLayoutNicknameSignUp.helperText =
                    signInViewModel.isEmpty(editTextUserName)
                textInputLayoutNameSignUp.helperText =
                    signInViewModel.isEmpty(editTextUserName)
                textInputLayoutSurnameSignUp.helperText =
                    signInViewModel.isEmpty(editTextUserName)

                if (!checkBoxStatus){
                    Toast.makeText(requireContext(), "Lütfen şartları ve gizlilik politikasını kabul edin", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }


                if (allFieldsValid()) {
                    signUpViewModel.onAction(
                        SignUpAction.RegisterUser(
                            SignUpDto(
                                email = editTextEmail,
                                firstName = editTextUserName,
                                lastName = editTextUserName,
                                password = editTextPassword,
                                passwordConfirm = editTextPasswordAgain,
                                userName = editTextUserName
                            )
                        )
                    )

                    signUpViewModel.signUpState.observe(viewLifecycleOwner) { signUpState ->
                        when {
                            signUpState.isLoading -> {
                                Log.e(
                                    getString(R.string.registerfragment),
                                    getString(R.string.loading)
                                )
                            }

                            signUpState.isError -> {
                                Log.e(
                                    getString(R.string.registerfragmenterror),
                                    signUpState.errorMessage
                                )
                            }

                            else -> {
                                SharedPreferencesHelper.saveUserAuthKey(
                                    requireContext(),
                                    signUpState.userAuthKey
                                )
                                navigate(RDirections.actionRegisterFragmentToSignUpFragment())
                            }
                        }
                    }
                }
            }

            binding.tvLoginSignUp.setOnClickListener {
                navigate(RDirections.actionRegisterFragmentToLoginFragment())
            }
        }
    }

    override fun navigate(action: NavDirections) {
        findNavController().navigate(action)
    }

    private fun allFieldsValid(): Boolean {
        return with(binding) {
                    textInputLayoutEmailSignUp.helperText == null
                    && textInputLayoutPasswordSignUp.helperText == null
                    && textInputLayoutPasswordAgainSignUp.helperText == null
                    && textInputLayoutNicknameSignUp.helperText == null
        }
    }

    private fun showPrivacyPolicyDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Şartlar ve Gizlilik Politkası")
        builder.setMessage("Bu uygulamanın şartlarını ve gizlilik sözleşmesini okudum ve kabul ediyorum.")

        builder.setPositiveButton("Okudum, Onaylıyorum") { dialog, _ ->
            dialog.dismiss()
        }

        builder.setNegativeButton("İptal") { dialog, _ ->
            binding.signUpTermsCheckbox.isChecked = false
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}



