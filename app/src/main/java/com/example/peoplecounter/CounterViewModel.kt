package com.example.peoplecounter

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class CounterViewModel(application: Application) : AndroidViewModel(application) {

    private val preferences: SharedPreferences by lazy {
        application.applicationContext.getSharedPreferences(
                        SHARED_PREFS_KEY, Context.MODE_PRIVATE) }

    private val _totalCount = MutableLiveData<Int>()
    val totalCount: LiveData<Int> get() = _totalCount

    private val _currentCount = MutableLiveData<Int>()
    val currentCount: LiveData<Int> get() = _currentCount

    init {
        if (preferences.contains(TOTAL_KEY) && preferences.contains(CURRENT_KEY)) {
            _totalCount.value = preferences.getInt(TOTAL_KEY, 0)
            _currentCount.value = preferences.getInt(CURRENT_KEY, 0)
        } else onReset()
    }

    fun onAdd() {
        _currentCount.value = _currentCount.value?.plus(1)
        _totalCount.value = _totalCount.value?.plus(1)
        preferences.edit().putInt(CURRENT_KEY, _currentCount.value ?: 0).apply()
        preferences.edit().putInt(TOTAL_KEY, _totalCount.value ?: 0).apply()
    }

    fun onSubtract() {
        _currentCount.value = _currentCount.value?.minus(1)
        preferences.edit().putInt(CURRENT_KEY, _currentCount.value ?: 0).apply()
    }

    fun onReset() {
        _currentCount.value = 0
        _totalCount.value = 0
        preferences.edit().putInt(CURRENT_KEY, 0).apply()
        preferences.edit().putInt(TOTAL_KEY, 0).apply()
    }

    companion object {
        const val SHARED_PREFS_KEY = "sharedPrefs"
        const val TOTAL_KEY = "total"
        const val CURRENT_KEY = "current"
    }
}