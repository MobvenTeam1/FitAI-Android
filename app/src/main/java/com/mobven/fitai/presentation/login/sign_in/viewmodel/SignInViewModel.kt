package com.mobven.fitai.presentation.login.sign_in.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobven.fitai.common.ResponseState
import com.mobven.fitai.data.model.dto.SignInDto
import com.mobven.fitai.domain.usecase.LoginUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {

    private val _signInUiState = MutableLiveData(SignInUiState().initial())
    val signInUiState : LiveData<SignInUiState> = _signInUiState

    fun onAction(action: SignInAction){
        when(action){
            is SignInAction.LoginUser -> loginUser(action.signInModel)
        }
    }

    private fun loginUser(signInModel: SignInDto){
        viewModelScope.launch {
            loginUserUseCase(signInModel).collect {
                when(it){
                    is ResponseState.Loading -> {
                        _signInUiState.value = _signInUiState.value?.copy(
                            isLoading = true
                        )
                    }
                    is ResponseState.Error -> {
                        _signInUiState.value = _signInUiState.value?.copy(
                            isLoading = false,
                            isError = true,
                            errorMessage = it.message
                        )
                    }
                    is ResponseState.Success -> {
                        _signInUiState.value = _signInUiState.value?.copy(
                            isLoading = false,
                            isError = false,
                            userAuthKey = it.data.token
                        )
                    }
                }
            }
        }
    }

    fun validEmail(emailText : String): String? {
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return "E-posta adresiniz hatalı!"
        }
        return null
    }

    fun validPhone(phoneText: String): String? {
        if (!Patterns.PHONE.matcher(phoneText).matches()) {
            return "Lütfen geçerli bir telefon numarası giriniz!"
        }
        return null
    }

    fun validPassword(passwordText: String) : String?{
        if (passwordText.length < 8){
            return "Şifreniz minimum 8 karakter olmalıdır."
        }
        if (!passwordText.matches(".*[A-Z].*".toRegex())){
            return "Şifreniz en az bir adet büyük harf içermelidir"
        }
        if (!passwordText.matches(".*[a-z].*".toRegex())){
            return "Şifreniz en az bir adet küçük harf içermelidir"
        }
        if (!passwordText.matches(".*[1-9].*".toRegex())){
            return "Şifreniz en az bir adet rakam içermelidir"
        }

        return null
    }

    fun isSamePassword(password : String, passwordConfirm : String) : String?{
        return if (password != passwordConfirm){
            "Parolalarınız eşleşmiyor."
        }else{
            null
        }
    }

    fun isEmpty(text : String) : String?{
        return if (text.isEmpty()){
            "Burası boş bırakılamaz."
        }else{
            null
        }
    }

}



data class SignInUiState(
    val isLoading : Boolean = false,
    val isError : Boolean = false,
    val errorMessage : String = "",
    val userAuthKey : String = ""
) {
    fun initial() = SignInUiState(isLoading = true)
}