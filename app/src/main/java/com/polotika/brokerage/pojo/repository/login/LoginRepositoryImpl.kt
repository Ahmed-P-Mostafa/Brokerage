package com.polotika.brokerage.pojo.repository.login

import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.polotika.brokerage.pojo.models.Event
import com.polotika.brokerage.pojo.models.User
import com.polotika.brokerage.pojo.repository.login.LoginRepository
import com.polotika.brokerage.webService.WebService
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val webService: WebService): LoginRepository {
    override fun logUserIn(email: String, password: String):Event {

        // fake auth
        return Event.Success
    }

    override fun registerUser(user: User): Event {
        return Event.Success
    }

    override fun forgetPassword(email: String): Event {

        return webService.forgetPasswordRequest(email)
    }

}