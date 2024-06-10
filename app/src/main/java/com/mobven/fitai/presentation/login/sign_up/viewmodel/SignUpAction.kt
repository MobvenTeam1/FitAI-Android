package com.mobven.fitai.presentation.login.sign_up.viewmodel

import com.mobven.fitai.data.model.dto.SignUpDto
import com.mobven.fitai.util.enums.SignUpFragmentType

sealed class SignUpAction {
    data class GetSelectorItem(val signUpFragmentType: SignUpFragmentType) : SignUpAction()
    data class RegisterUser(val signUpDto: SignUpDto) : SignUpAction()
    data class EnterFirstLogin(val userAuthKey: String) : SignUpAction()
    data class EnterGender(val gender: String) : SignUpAction()
    data class EnterHeight(val height: Int) : SignUpAction()
    data class EnterWeight(val weight: Int) : SignUpAction()
    data class EnterWeightGoal(val weightGoal: Int) : SignUpAction()
    data class EnterBirthday(val birthday: String) : SignUpAction()
    data class EnterProfileGoals(val profileGoals: String) : SignUpAction()
}
