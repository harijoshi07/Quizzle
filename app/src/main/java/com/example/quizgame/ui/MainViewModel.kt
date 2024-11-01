package com.example.quizgame.ui

import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.quizgame.data.SettingPreferences
import kotlinx.coroutines.launch

class MainViewModel(private val settingPreferences: SettingPreferences) : ViewModel() {

    fun getToken() = settingPreferences.getToken().asLiveData()

    fun saveToken(token: String) {
        viewModelScope.launch {
            settingPreferences.saveToken(token)
        }
    }
}