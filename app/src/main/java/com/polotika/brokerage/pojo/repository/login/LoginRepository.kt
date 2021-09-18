package com.polotika.brokerage.pojo.repository.login

import com.polotika.brokerage.pojo.models.Event
import com.polotika.brokerage.pojo.models.User

interface LoginRepository {
    fun logUserIn(email:String,password:String):Event
    fun registerUser(user: User):Event
}