package com.polotika.brokerage.di

import com.polotika.brokerage.pojo.repository.login.LoginRepository
import com.polotika.brokerage.pojo.repository.login.LoginRepositoryImpl
import com.polotika.brokerage.pojo.repository.login.LoginUseCase
import com.polotika.brokerage.pojo.repository.register.RegisterRepository
import com.polotika.brokerage.pojo.repository.register.RegisterRepositoryImpl
import com.polotika.brokerage.pojo.repository.register.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideLoginUseCase(repository: LoginRepository):LoginUseCase{
        return LoginUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideLoginRepository():LoginRepository{
        return LoginRepositoryImpl()
    }

    @Singleton
    @Provides
    fun provideRegisterUseCase(repository: RegisterRepository):RegisterUseCase{
        return RegisterUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideRegisterRepository():RegisterRepository{
        return RegisterRepositoryImpl()
    }


}