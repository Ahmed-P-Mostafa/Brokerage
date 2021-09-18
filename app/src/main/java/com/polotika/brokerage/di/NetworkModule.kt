package com.polotika.brokerage.di

import android.content.Context
import com.polotika.brokerage.pojo.repository.login.ForgetUseCase
import com.polotika.brokerage.pojo.repository.login.LoginRepository
import com.polotika.brokerage.webService.WebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Provides
    @Singleton
    fun provideWebService(@ApplicationContext context: Context):WebService{
        return WebService(context)
    }

}