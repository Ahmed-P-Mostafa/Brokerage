package com.polotika.brokerage.pojo.repository.main

import android.content.Context
import com.polotika.brokerage.R
import com.polotika.brokerage.pojo.models.ServiceItem

class MainRepositoryImpl(private val context: Context) :MainRepository {
    override fun getServicesList(): List<ServiceItem> {
        return listOf(
            ServiceItem(context.getString(R.string.service1),true,R.drawable.logo),
            ServiceItem(context.getString(R.string.service2),false,R.drawable.logo),
            ServiceItem(context.getString(R.string.service3),false,R.drawable.logo),
            ServiceItem(context.getString(R.string.service3),true,R.drawable.logo),
            ServiceItem(context.getString(R.string.service5),true,R.drawable.logo),
            ServiceItem(context.getString(R.string.service6),true,R.drawable.logo),
            ServiceItem(context.getString(R.string.service7),false,R.drawable.logo),
            ServiceItem(context.getString(R.string.service8),false,R.drawable.logo),
            ServiceItem(context.getString(R.string.service9),false,R.drawable.logo),
            ServiceItem(context.getString(R.string.service10),false,R.drawable.logo),
            ServiceItem(context.getString(R.string.service11),false,R.drawable.logo),
        )
    }
}