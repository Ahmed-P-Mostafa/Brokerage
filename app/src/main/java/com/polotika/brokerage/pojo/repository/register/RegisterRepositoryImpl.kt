package com.polotika.brokerage.pojo.repository.register

import com.polotika.brokerage.pojo.models.Event
import com.polotika.brokerage.pojo.models.User

class RegisterRepositoryImpl:RegisterRepository {
    override fun registerUser(user: User): Event {
        return Event.Success
    }
}