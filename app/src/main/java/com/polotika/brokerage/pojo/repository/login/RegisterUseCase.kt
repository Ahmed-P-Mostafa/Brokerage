package com.polotika.brokerage.pojo.repository.login

import com.polotika.brokerage.pojo.models.Event
import com.polotika.brokerage.pojo.models.User

class RegisterUseCase(private val repository: LoginRepository) {

    fun execute(user: User): Event {
        return repository.registerUser(user)
    }
}