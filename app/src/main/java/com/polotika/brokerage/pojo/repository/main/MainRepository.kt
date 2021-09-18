package com.polotika.brokerage.pojo.repository.main

import com.polotika.brokerage.pojo.models.ServiceItem

interface MainRepository {

    fun getServicesList(): List<ServiceItem>
}