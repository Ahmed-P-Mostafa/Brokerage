package com.polotika.brokerage.pojo.repository.main

import com.polotika.brokerage.pojo.models.ServiceItem

class ServicesListUseCase(private val mainRepository: MainRepository) {
    fun execute(): List<ServiceItem> {
        return mainRepository.getServicesList()
    }
}