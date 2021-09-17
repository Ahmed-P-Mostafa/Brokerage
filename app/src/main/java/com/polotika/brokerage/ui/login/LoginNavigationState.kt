package com.polotika.brokerage.ui.login

sealed class LoginNavigationState {
    object LoginSucceed:LoginNavigationState()
    object LoginFailed:LoginNavigationState()

}