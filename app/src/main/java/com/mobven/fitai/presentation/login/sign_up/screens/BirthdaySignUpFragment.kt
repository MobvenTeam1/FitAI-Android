package com.mobven.fitai.presentation.login.sign_up.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.mobven.fitai.R
import com.mobven.fitai.databinding.FragmentBirthdaySignUpBinding
import com.mobven.fitai.presentation.base.BaseFragment
import com.mobven.fitai.presentation.login.sign_up.viewmodel.SignUpAction
import com.mobven.fitai.presentation.login.sign_up.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("SimpleDateFormat")
class BirthdaySignUpFragment :
    BaseFragment<FragmentBirthdaySignUpBinding>(FragmentBirthdaySignUpBinding::inflate) {

    private val viewModel: SignUpViewModel by activityViewModels()

    override fun observeUi() {
        with(binding) {
            binding.btnBirthdayContinue.setOnClickListener {
                val birthdayCondition =
                    etBirthdayDay.text.toString().isEmpty() && etBirthdayMonth.text.toString()
                        .isEmpty() && etBirthdayYear.text.toString().isEmpty()

                if (birthdayCondition) {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.please_enter_your_birthday),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                } else {
                    val givenBirthday = "${etBirthdayYear.text}-${etBirthdayMonth.text}-${etBirthdayDay.text}"

                    try {
                        viewModel.onAction(SignUpAction.EnterBirthday(givenBirthday))
                    }catch (e: Exception){
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.please_enter_a_valid_date),
                            Toast.LENGTH_SHORT
                        ).show()
                        return@setOnClickListener
                    }

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
