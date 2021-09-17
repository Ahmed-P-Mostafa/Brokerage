package com.polotika.brokerage.pojo.repository.login

import com.polotika.brokerage.pojo.models.Event
import com.polotika.brokerage.pojo.repository.login.LoginRepository

class LoginRepositoryImpl: LoginRepository {
    override fun logUserIn(email: String, password: String):Event {

        // fake auth
        return Event.Success
    }

}