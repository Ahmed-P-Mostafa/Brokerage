package com.polotika.brokerage.ui.splash

import androidx.lifecycle.MutableLiveData
import com.polotika.brokerage.base.BaseNavigator
import com.polotika.brokerage.base.BaseViewModel
import com.polotika.brokerage.utils.PreferencesUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val prefs: PreferencesUtils) :
    BaseViewModel<BaseNavigator>() {

    val splashNavigationEvent = MutableLiveData<SplashNavigationEvent>()
    val appDefaultLocale = MutableLiveData<String>()


    init {
        CoroutineScope(IO).launch {
            appDefaultLocale.postValue(prefs.appDefaultLocale.first())
            when {
                prefs.isAppOnboarded.first() -> {
                    splashNavigationEvent.postValue(SplashNavigationEvent.OnBoardEvent)
                }
                prefs.isUserLogin.first() -> {
                    splashNavigationEvent.postValue(SplashNavigationEvent.HomeEvent)
                }
                prefs.isUserLogin.first().not() -> {
                    splashNavigationEvent.postValue(SplashNavigationEvent.LoginEvent)
                }
            }

        }

    }

}