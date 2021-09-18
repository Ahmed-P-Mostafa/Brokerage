package com.polotika.brokerage.pojo.repository.login

import com.polotika.brokerage.pojo.models.Event

class LoginUseCase(private val repository: LoginRepository) {

    fun execute(email: String, password: String): Event {
        return repository.logUserIn(email, password)
    }
}