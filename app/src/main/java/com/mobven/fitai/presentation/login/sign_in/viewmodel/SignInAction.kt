package com.mobven.fitai.presentation.login.sign_in.viewmodel

import com.mobven.fitai.data.model.dto.SignInDto

sealed class SignInAction {
    data class LoginUser(val signInModel: SignInDto) : SignInAction()
}