package com.mobven.fitai.presentation.login.sign_up.screens

import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.mobven.fitai.R
import com.mobven.fitai.databinding.FragmentBirthdaySignUpBinding
import com.mobven.fitai.presentation.base.BaseFragment
import com.mobven.fitai.presentation.login.sign_up.viewmodel.SignUpAction
import com.mobven.fitai.presentation.login.sign_up.viewmodel.SignUpViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class BirthdaySignUpFragment :
    BaseFragment<FragmentBirthdaySignUpBinding>(FragmentBirthdaySignUpBinding::inflate) {

    private val viewModel: SignUpViewModel by activityViewModels()

    private lateinit var editTextDay: EditText
    private lateinit var editTextMonth: EditText
    private lateinit var editTextYear: EditText

    @RequiresApi(Build.VERSION_CODES.O)
    override fun observeUi() {
        with(binding) {
            btnBirthdayContinue.setOnClickListener {
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
                    val givenBirthday =
                        etBirthdayDay.text.toString() + getString(R.string.slash) + etBirthdayMonth.text.toString() + getString(
                            R.string.slash
                        ) + etBirthdayYear.text.toString()

                    viewModel.onAction(SignUpAction.EnterBirthday(givenBirthday))

                    val currentItem =
                        requireActivity().findViewById<ViewPager2>(R.id.sign_up_view_pager).currentItem
                    val nextItem = currentItem + 1
                    requireActivity().findViewById<ViewPager2>(R.id.sign_up_view_pager)
                        .setCurrentItem(nextItem, true)
                }

                editTextDay = etBirthdayDay
                editTextMonth = etBirthdayMonth
                editTextYear = etBirthdayYear

                focusEditText(editTextDay, editTextMonth)
                focusEditText(editTextMonth, editTextYear)

                btnBirthdayContinue.setOnClickListener {
                    val birthDay: String =
                        "${editTextDay.getText()}-${editTextMonth.getText()}-${editTextYear.getText()}"
                    println(birthDay)
                    if (!isDateOfBirthValid(birthDay)) {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.please_give_valid_time),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val currentItem =
                            requireActivity().findViewById<ViewPager2>(R.id.sign_up_view_pager).currentItem
                        val nextItem = currentItem + 1
                        requireActivity().findViewById<ViewPager2>(R.id.sign_up_view_pager)
                            .setCurrentItem(nextItem, true)
                    }
                }
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun isDateOfBirthValid(date: String): Boolean {
            try {
                val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
                val localDate = LocalDate.parse(date, formatter)
                return !(formatter.format(localDate) != date || localDate > LocalDate.now())
            } catch (e: Exception) {
                return false
            }
        }

        fun focusEditText(edittextFirst: EditText, editTextSecond: EditText) {
            edittextFirst.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 2) {
                        editTextSecond.requestFocus()
                    }
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }
    }
}