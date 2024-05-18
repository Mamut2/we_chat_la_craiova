package com.mamut.wechatlacraiova

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")

class User(context: Context){
    private val dataStore = context.dataStore

    val isUserLoggedIn: Flow<Boolean> = dataStore.data.map {preferences -> preferences[USER_LOGGED_IN] ?: false}

    suspend fun saveUser(isUserLoggedIn: Boolean){
        dataStore.edit {preferences -> preferences[USER_LOGGED_IN] = isUserLoggedIn}
    }

    companion object{
        private val USER_LOGGED_IN = booleanPreferencesKey("user_logged_in")
    }

}