package com.polotika.brokerage.ui.register

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.polotika.brokerage.base.BaseNavigator
import com.polotika.brokerage.base.BaseViewModel
import com.polotika.brokerage.pojo.models.Event
import com.polotika.brokerage.pojo.models.User
import com.polotika.brokerage.pojo.repository.login.RegisterUseCase
import com.polotika.brokerage.utils.PreferencesUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val prefs: PreferencesUtils,
    private val registerUseCase: RegisterUseCase
) : BaseViewModel<BaseNavigator>() {

    private val TAG = "RegisterViewModel"
    //^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$
    private val PASSWORD_PETTERN = "^(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"

    val firstName = MutableLiveData<String>()
    val mobileNumber = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()
    val isChecked = MutableLiveData<Boolean>(true)

    val firstNameError = MutableLiveData<Boolean>()
    val lastNameError = MutableLiveData<Boolean>()
    val emailError = MutableLiveData<Boolean>()
    val passwordError = MutableLiveData<Boolean>()
    val mobileError = MutableLiveData<Boolean>()

    val registerNavigationEvent = MutableLiveData<RegisterNavigationState>()

    fun register() {
        Log.d(TAG, "register: ${isDataValid()}")
        if (isDataValid()) {
            when (registerUseCase.execute(collectUserData())) {
                Event.Success -> {
                    completeRegister()
                }
                Event.Failed -> {
                    registerNavigationEvent.value = RegisterNavigationState.LoginFailed
                }
            }
        } else {
            showViewErrors()
            registerNavigationEvent.value = RegisterNavigationState.LoginFailed
        }
    }

    private fun completeRegister() {
        registerNavigationEvent.value = RegisterNavigationState.LoginSucceed
        viewModelScope.launch {
            prefs.userLogin()
        }
    }

    private fun isDataValid(): Boolean {
        return !(firstName.value.isNullOrBlank() || lastName.value.isNullOrBlank() ||
                email.value.isNullOrBlank() || mobileNumber.value.isNullOrBlank() ||
                password.value.isNullOrBlank() || isChecked.value == false ||
                !Pattern.compile(PASSWORD_PETTERN).matcher(password.value.toString()).matches()||
                !Patterns.EMAIL_ADDRESS.matcher(email.value.toString()).matches())
    }

    private fun showViewErrors() {
        firstNameError.value = firstName.value.isNullOrEmpty()
        lastNameError.value = lastName.value.isNullOrEmpty()
        emailError.value = email.value.isNullOrEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email.value.toString()).matches()
        mobileError.value = mobileNumber.value?.length?:0 < 11
        passwordError.value = password.value?.length?:0 < 8||
                !Pattern.compile(PASSWORD_PETTERN).matcher(password.value.toString()).matches()

    }

    private fun collectUserData(): User {
        return User(
            name = firstName.value + " " + lastName.value,
            email = email.value,
            password = password.value,
            mobileNumber = mobileNumber.value
        )
    }
}