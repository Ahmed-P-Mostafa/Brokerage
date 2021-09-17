package com.polotika.brokerage.pojo.repository.register

import com.polotika.brokerage.pojo.models.Event
import com.polotika.brokerage.pojo.models.User

interface RegisterRepository {
    fun registerUser(user: User):Event
}