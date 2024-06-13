package com.mobven.fitai.presentation.login.sign_up.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobven.fitai.R
import com.mobven.fitai.common.ResponseState
import com.mobven.fitai.data.model.dto.FirstLoginDto
import com.mobven.fitai.data.model.dto.SignUpDto
import com.mobven.fitai.domain.usecase.RegisterUserUseCase
import com.mobven.fitai.domain.usecase.SaveFirstLoginUseCase
import com.mobven.fitai.infrastructure.string_resource.StringResourceProvider
import com.mobven.fitai.presentation.login.sign_up.model.ListSelectorItem
import com.mobven.fitai.util.enums.SignUpFragmentType
import com.mobven.fitai.util.enums.SignUpSelectorType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val stringRes: StringResourceProvider,
    private val registerUserUseCase: RegisterUserUseCase,
    private val saveFirstLoginUseCase: SaveFirstLoginUseCase
) : ViewModel() {

    private val _signUpState = MutableLiveData(SignUpState.initial())
    val signUpState: LiveData<SignUpState> = _signUpState

    private var selectedGender: String = ""
    private var givenWeight: Int = 0
    private var givenHeight: Int = 0
    private var givenWeightGoal: Int = 0
    private var givenBirthday: String = ""
    private var selectedGoal: String = ""

    var signUpSelectorList: List<ListSelectorItem> = emptyList()

    fun onAction(action: SignUpAction) {
        when (action) {
            is SignUpAction.GetSelectorItem -> {
                getSignUpSelectorList(action.signUpFragmentType)
            }

            is SignUpAction.RegisterUser -> {
                registerUser(action.signUpDto)
            }

            is SignUpAction.EnterFirstLogin -> {
                saveFirstLogin(action.userAuthKey)
            }

            is SignUpAction.EnterGender -> {
                enterSelectedGender(action.gender)
            }

            is SignUpAction.EnterHeight -> {
                enterGivenHeight(action.height)
            }

            is SignUpAction.EnterWeight -> {
                enterGivenWeight(action.weight)
            }

            is SignUpAction.EnterWeightGoal -> {
                enterGivenWeightGoal(action.weightGoal)
            }

            is SignUpAction.EnterBirthday -> {
                enterGivenBirthday(action.birthday)
            }

            is SignUpAction.EnterProfileGoals -> {
                enterSelectedGoal(action.profileGoals)
            }
        }
    }

    private fun getSignUpSelectorList(signUpFragmentType: SignUpFragmentType) {
        this.signUpSelectorList = when (signUpFragmentType) {
            SignUpFragmentType.GENDER -> {
                listOf(
                    ListSelectorItem(
                        title = stringRes.getString(R.string.other_gender),
                        isSelected = false,
                        type = SignUpSelectorType.RADIO,
                        image = R.drawable.other_gender_icon,
                    ),
                    ListSelectorItem(
                        title = stringRes.getString(R.string.male_gender),
                        isSelected = false,
                        type = SignUpSelectorType.RADIO,
                        image = R.drawable.male_icon,
                    ),
                    ListSelectorItem(
                        title = stringRes.getString(R.string.female_gender),
                        isSelected = false,
                        type = SignUpSelectorType.RADIO,
                        image = R.drawable.female_icon,
                    ),
                )
            }

            SignUpFragmentType.GOALS -> {
                listOf(
                    ListSelectorItem(
                        title = stringRes.getString(R.string.healthy_life),
                        isSelected = false,
                        type = SignUpSelectorType.RADIO,
                        image = R.drawable.healthy_life,
                    ),
                    ListSelectorItem(
                        title = stringRes.getString(R.string.muscle_gain),
                        isSelected = false,
                        type = SignUpSelectorType.RADIO,
                        image = R.drawable.increase_muscle,
                    ),
                    ListSelectorItem(
                        title = stringRes.getString(R.string.weight_gain),
                        isSelected = false,
                        type = SignUpSelectorType.RADIO,
                        image = R.drawable.increase_weight,
                    ),
                    ListSelectorItem(
                        title = stringRes.getString(R.string.weight_loss),
                        isSelected = false,
                        type = SignUpSelectorType.RADIO,
                        image = R.drawable.weight_down,
                    ),
                )
            }
        }
    }

    private fun registerUser(signUpDto: SignUpDto) {
        viewModelScope.launch {
            registerUserUseCase(signUpDto).collect { registerResponse ->
                when (registerResponse) {
                    is ResponseState.Error -> {
                        _signUpState.value = _signUpState.value?.copy(
                            isLoading = false,
                            isError = true,
                            errorMessage = registerResponse.message
                        )
                    }

                    ResponseState.Loading -> {
                        _signUpState.value = _signUpState.value?.copy(
                            isLoading = true,
                            isError = false
                        )
                    }

                    is ResponseState.Success -> {
                        _signUpState.value = SignUpState(
                            isError = false,
                            isLoading = false,
                            isRegisterSuccess = true,
                            userAuthKey = registerResponse.data.token
                        )
                    }
                }
            }
        }
    }

    private fun saveFirstLogin(userAuthKey: String) {
        viewModelScope.launch {
            saveFirstLoginUseCase(
                userAuthKey,
                FirstLoginDto(
                    dateOfBirth = this@SignUpViewModel.givenBirthday,
                    firstWeight = this@SignUpViewModel.givenWeight,
                    height = this@SignUpViewModel.givenHeight,
                    targetWeight = this@SignUpViewModel.givenWeightGoal,
                    goals = this@SignUpViewModel.selectedGoal,
                    gender = this@SignUpViewModel.selectedGender
                )
            ).collect {
                when (it) {
                    is ResponseState.Error -> {
                        _signUpState.value = _signUpState.value?.copy(
                            isLoading = false,
                            isError = true,
                            errorMessage = it.message
                        )
                    }

                    ResponseState.Loading -> {
                        _signUpState.value = _signUpState.value?.copy(
                            isLoading = true,
                            isError = false
                        )
                    }

                    is ResponseState.Success -> {
                        _signUpState.value = _signUpState.value?.copy(
                            isLoading = false,
                            isError = false,
                            isFirstLoginSuccess = true
                        )
                    }
                }
            }
        }
    }

    private fun enterSelectedGender(selectedGender: String) {
        this.selectedGender = selectedGender
    }

    private fun enterGivenWeight(givenWeight: Int) {
        this.givenWeight = givenWeight
    }

    private fun enterGivenHeight(givenHeight: Int) {
        this.givenHeight = givenHeight
    }

    private fun enterGivenWeightGoal(givenWeightGoal: Int) {
        this.givenWeightGoal = givenWeightGoal
    }

    private fun enterGivenBirthday(givenBirthday: String) {
        this.givenBirthday = givenBirthday
    }

    private fun enterSelectedGoal(selectedGoal: String) {
        this.selectedGoal = selectedGoal
    }
}

data class SignUpState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isRegisterSuccess: Boolean = false,
    val isFirstLoginSuccess: Boolean = false,
    val errorMessage: String = "",
    val successResponse: String = "",
    val userAuthKey: String = ""
) {
    companion object {
        fun initial() = SignUpState(isLoading = true)
    }
}
