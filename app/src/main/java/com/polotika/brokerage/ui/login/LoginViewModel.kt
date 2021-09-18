package com.polotika.brokerage.ui.login

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.polotika.brokerage.base.BaseNavigator
import com.polotika.brokerage.base.BaseViewModel
import com.polotika.brokerage.pojo.models.Event
import com.polotika.brokerage.pojo.repository.login.LoginUseCase
import com.polotika.brokerage.utils.PreferencesUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val prefs: PreferencesUtils,
    private val loginUseCase: LoginUseCase
) : BaseViewModel<BaseNavigator>() {

    private val TAG = "LoginViewModel"
    private val email = MutableStateFlow<String>("")
    private val password = MutableStateFlow<String>("")
    val navigationEvent = MutableLiveData<LoginNavigationState>()

    fun setEmail(email: String) {
        this.email.value = email
    }

    fun setPassword(value: String) {
        password.value = value
    }

    val emailCheck: Flow<Boolean> = combine(email, password) { email, _ ->
        return@combine Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    val passwordCheck: Flow<Boolean> = combine(email, password) { _, password ->
        return@combine password.length >= 8
    }

    fun login() {
        viewModelScope.launch {
            if (isDataValid()) {
                when (loginUseCase.execute(email.value, password.value)) {
                    is Event.Success -> {
                        completeLogin()
                    }
                    is Event.Failed -> {
                        navigationEvent.value = LoginNavigationState.LoginFailed
                    }
                }
            } else {
                navigationEvent.value = LoginNavigationState.LoginFailed
            }
        }
    }

    private suspend fun isDataValid(): Boolean {
        return emailCheck.first() && passwordCheck.first()
    }

    private fun completeLogin() {
        navigationEvent.value = LoginNavigationState.LoginSucceed
        viewModelScope.launch {
            prefs.userLogin()
        }
    }
}