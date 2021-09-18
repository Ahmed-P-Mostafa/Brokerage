package com.polotika.brokerage.pojo.repository.login

import com.polotika.brokerage.pojo.models.Event


class ForgetUseCase(private val repository: LoginRepository) {
    fun execute(email:String): Event{
        return repository.forgetPassword(email)
    }
}