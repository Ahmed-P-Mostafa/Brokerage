package com.polotika.brokerage.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.polotika.brokerage.base.BaseNavigator
import com.polotika.brokerage.base.BaseViewModel
import com.polotika.brokerage.pojo.models.Event
import com.polotika.brokerage.pojo.models.User
import com.polotika.brokerage.pojo.repository.register.RegisterUseCase
import com.polotika.brokerage.ui.login.LoginNavigationState
import com.polotika.brokerage.utils.PreferencesUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val prefs: PreferencesUtils,
    private val registerUseCase: RegisterUseCase
) : BaseViewModel<BaseNavigator>() {

    val firstName = MutableLiveData<String>()
    val mobileNumber = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()

    val registerNavigationEvent = MutableLiveData<RegisterNavigationState>()

    fun register() {
        if (isDataValid()) {
            when (registerUseCase.execute(collectUserData())) {
                Event.Success -> {
                    completeRegister()
                }
                Event.Failed -> {
                    registerNavigationEvent.value= RegisterNavigationState.LoginFailed
                }
            }
        } else {
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
        return (!firstName.value.isNullOrBlank() && lastName.value.isNullOrBlank() &&
                email.value.isNullOrBlank() && mobileNumber.value.isNullOrBlank() &&
                password.value.isNullOrBlank())
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