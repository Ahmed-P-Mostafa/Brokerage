package com.polotika.brokerage.di

import android.content.Context
import com.polotika.brokerage.pojo.repository.login.*
import com.polotika.brokerage.pojo.repository.main.MainRepository
import com.polotika.brokerage.pojo.repository.main.MainRepositoryImpl
import com.polotika.brokerage.pojo.repository.main.ServicesListUseCase
import com.polotika.brokerage.webService.WebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideLoginUseCase(repository: LoginRepository): LoginUseCase {
        return LoginUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideLoginRepository(webService: WebService): LoginRepository {
        return LoginRepositoryImpl(webService)
    }

    @Singleton
    @Provides
    fun provideRegisterUseCase(repository: LoginRepository): RegisterUseCase {
        return RegisterUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideMainRepository(@ApplicationContext context: Context): MainRepository {
        return MainRepositoryImpl(context)
    }

    @Singleton
    @Provides
    fun provideServicesListUseCase(mainRepository: MainRepository): ServicesListUseCase {
        return ServicesListUseCase(mainRepository)
    }

    @Singleton
    @Provides
    fun provideForgetUseCase(loginRepository: LoginRepository): ForgetUseCase {
        return ForgetUseCase(loginRepository)
    }


}