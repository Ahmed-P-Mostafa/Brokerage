package com.polotika.brokerage.ui.register

sealed class RegisterNavigationState {
    object LoginSucceed : RegisterNavigationState()
    object LoginFailed : RegisterNavigationState()

}