package com.polotika.brokerage.pojo.repository.login

import com.polotika.brokerage.pojo.models.Event

interface LoginRepository {
    fun logUserIn(email:String,password:String):Event
}