package com.polotika.brokerage.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.polotika.brokerage.pojo.AppConstants.PREFS_BOARDED_KEY
import com.polotika.brokerage.pojo.AppConstants.PREFS_DATA_STORE_NAME
import com.polotika.brokerage.pojo.AppConstants.PREFS_USER_LOGIN_KEY
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFS_DATA_STORE_NAME)

class PreferencesUtils @Inject constructor(@ApplicationContext private val context: Context) {

    // At the top level of your kotlin file:

    private val isAppOnBoardedKey = booleanPreferencesKey(PREFS_BOARDED_KEY)
    private val isUserLoginKey = booleanPreferencesKey(PREFS_USER_LOGIN_KEY)

    val isAppOnboarded: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[isAppOnBoardedKey] ?: false
    }

    val isUserLogin:Flow<Boolean> = context.dataStore.data.map { prefs->
        prefs[isUserLoginKey]?:false
    }

    suspend fun completeOnBoarding() {
        context.dataStore.edit { settings ->
            settings[isAppOnBoardedKey] = true
        }
    }

    suspend fun userLogin(){
        context.dataStore.edit { settings->
            settings[isUserLoginKey] = true
        }
    }

    suspend fun userLogout(){
        context.dataStore.edit { settings->
            settings[isUserLoginKey] = false
        }
    }
}