package com.polotika.brokerage.pojo.repository.login

import com.polotika.brokerage.pojo.models.Event


class ForgetUseCase(private val repository: LoginRepository) {
    fun execute(email: String): Boolean {
        return when (repository.forgetPassword(email)) {
            Event.Failed -> {
                false
            }
            Event.Success -> true
            else -> {
                false
            }

        }
    }
}