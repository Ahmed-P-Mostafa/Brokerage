package com.polotika.brokerage.pojo.repository.register

import com.polotika.brokerage.pojo.models.Event
import com.polotika.brokerage.pojo.models.User

class RegisterUseCase(private val repository: RegisterRepository) {

    fun execute(user: User):Event{
        return repository.registerUser(user)
    }
}