package com.study.disgroupportal.viewmodel

import android.app.Application
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.study.disgroupportal.data.DataSource
import com.study.disgroupportal.data.DataSource.clearDb
import com.study.disgroupportal.data.DataSource.getUser
import com.study.disgroupportal.data.DataSource.setDbPresets
import com.study.disgroupportal.model.employee.Employee
import com.study.disgroupportal.model.navigation.Destination
import com.study.disgroupportal.model.navigation.DestinationArg
import com.study.disgroupportal.model.navigation.NavArgument
import com.study.disgroupportal.tools.Navigation.navigateTo
import com.study.disgroupportal.tools.SharedPrefs
import com.study.disgroupportal.tools.logE
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application,
) : AndroidViewModel(application) {
    private val prefs = SharedPrefs(application)

    var user by mutableStateOf<Employee?>(null)
    var pendingLogin by mutableStateOf(false)

    fun login(
        login: String,
        password: String,
        isAdministrateMode: Boolean,
        onSuccess: () -> Unit = {},
        onFailure: (Throwable) -> Unit = {},
    ) {
        pendingLogin = true
        viewModelScope.launch {
            DataSource.login(
                isAdministrateMode = isAdministrateMode,
                password = password,
                login = login
            ).onFailure { onFailure(it) }.onSuccess {
                prefs.saveToken(it.id)
                user = it
                onSuccess()
            }
            pendingLogin = false
        }
    }

    fun updateUser() = viewModelScope.launch {
        getUser(prefs.authToken).onSuccess { user = it }
    }

    fun logout() {
        user = null
        pendingLogin = false
        prefs.clearToken()
    }

    fun setDatabasePresets() = viewModelScope
        .launch { setDbPresets() }

    fun clearDatabase() = viewModelScope
        .launch { clearDb() }
}