package com.polotika.brokerage.base

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polotika.brokerage.utils.PreferencesUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.*

open class BaseViewModel<N> : ViewModel() {
    val message = MutableLiveData<String>()
    val loader = MutableLiveData<Boolean>()
    var navigator: N? = null


    fun setAppLocale(context: Context, language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = context.resources.configuration
        config.setLocale(locale)
        context.createConfigurationContext(config)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
        setLanguagePrefs(context, language)
    }

    private fun setLanguagePrefs(context: Context, language: String) {
        CoroutineScope(IO).launch {
            PreferencesUtils(context).setAppDefaultLocale(language)
        }
    }
}