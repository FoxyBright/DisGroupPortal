package com.study.disgroupportal.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.study.disgroupportal.data.DataSource
import com.study.disgroupportal.data.DataSource.clearDb
import com.study.disgroupportal.data.DataSource.editRequest
import com.study.disgroupportal.data.DataSource.getAllRequests
import com.study.disgroupportal.data.DataSource.getAllEmployees
import com.study.disgroupportal.data.DataSource.getNews
import com.study.disgroupportal.data.DataSource.getUser
import com.study.disgroupportal.data.DataSource.getUserRequests
import com.study.disgroupportal.data.DataSource.loginInGosuslugi
import com.study.disgroupportal.data.DataSource.setDbPresets
import com.study.disgroupportal.model.news.New
import com.study.disgroupportal.model.requests.Request
import com.study.disgroupportal.model.requests.RequestStatus
import com.study.disgroupportal.model.requests.RequestTheme
import com.study.disgroupportal.model.employee.Employee
import com.study.disgroupportal.model.employee.UserRole.ADMIN
import com.study.disgroupportal.tools.SharedPrefs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application,
) : AndroidViewModel(application) {
    private val prefs = SharedPrefs(application)

    val news = mutableStateListOf<New>()
    var user by mutableStateOf<Employee?>(null)

    var firstUploadRequests by mutableStateOf(true)
    var pendingRequests by mutableStateOf(false)
    var pendingLogin by mutableStateOf(false)
    var pendingNews by mutableStateOf(false)

    var refreshRequests by mutableStateOf(false)
    var refreshNews by mutableStateOf(false)

    val employees = mutableStateListOf<Employee>()
    var pendingEmployees by mutableStateOf(false)
    var refreshEmployees by mutableStateOf(false)

    val searchText = mutableStateOf("")

    private val _requestStatusFilters =
        MutableStateFlow<List<RequestStatus>>(emptyList())
    val requestStatusFilters =
        _requestStatusFilters.asStateFlow()

    private val _requestThemeFilters =
        MutableStateFlow<List<RequestTheme>>(emptyList())
    val requestThemeFilters =
        _requestThemeFilters.asStateFlow()

    fun changeThemeFilter(theme: RequestTheme) {
        val list = _requestThemeFilters.value
        if (list.contains(theme)) {
            _requestThemeFilters.value = list - theme
        } else {
            _requestThemeFilters.value = list + theme
        }
    }

    private val _requests = MutableStateFlow<List<Request>>(emptyList())
    val requests = _requests.asStateFlow()

    fun changeStatusFilter(theme: RequestStatus) {
        val list = _requestStatusFilters.value
        if (list.contains(theme)) {
            _requestStatusFilters.value = list - theme
        } else {
            _requestStatusFilters.value = list + theme
        }
    }

    fun clearFilters() {
        _requestThemeFilters.value = emptyList()
        _requestStatusFilters.value = emptyList()
    }

    fun login(
        login: String,
        password: String,
        isAdministrateMode: Boolean,
        onSuccess: () -> Unit = {},
        onFailure: (Throwable) -> Unit = {},
    ) {
        pendingLogin = true
        viewModelScope.launch {
            loginInGosuslugi(
                isAdministrateMode = isAdministrateMode,
                password = password,
                login = login
            ).onFailure { onFailure(it) }.onSuccess {
                prefs.saveToken(it.id.toString())
                user = it
                onSuccess()
            }
            pendingLogin = false
        }
    }

    fun uploadNews() {
        pendingNews = true
        viewModelScope.launch {
            getNews().onSuccess {
                news.clear()
                news.addAll(it)
            }
            pendingNews = false
            refreshNews = false
        }
    }

    fun updateUser() {
        viewModelScope.launch {
            getUser(prefs.authToken.toLong())
                .onSuccess { user = it }
        }
    }

    fun uploadRequests() {
        pendingRequests = true
        firstUploadRequests = false
        viewModelScope.launch {
            val list = mutableStateListOf<Request>()
            when (user?.role) {
                ADMIN -> getAllRequests()
                    .onSuccess { list.addAll(it) }

                else -> getUserRequests(user?.id)
                    .onSuccess { list.addAll(it) }
            }
            _requests.value = list
            pendingRequests = false
            refreshRequests = false
        }
    }

    fun logout() {
        news.clear()
        user = null
        pendingLogin = false
        pendingNews = false
        prefs.clearToken()
    }

    fun setDatabasePresets() {
        viewModelScope.launch {
            setDbPresets()
            uploadNews()
            uploadRequests()
            uploadEmployees()
        }
    }

    fun clearDatabase() {
        viewModelScope.launch {
            clearDb()
            uploadNews()
            uploadRequests()
            uploadEmployees()
        }
    }

    fun answerRequest(request: Request) {
        viewModelScope.launch {
            editRequest(request)
            uploadRequests()
        }
    }

    fun addRequest(request: Request) {
        viewModelScope.launch {
            DataSource.addRequest(request)
            uploadRequests()
        }
    }

    fun deleteRequest(request: Request) {
        viewModelScope.launch {
            DataSource.deleteRequest(request)
            uploadRequests()
        }
    }

    fun addNew(new: New) {
        viewModelScope.launch {
            DataSource.addNew(new)
            uploadNews()
        }
    }

    fun addEmployee(employee: Employee) {
        viewModelScope.launch {
            DataSource.addEmployee(employee)
            uploadEmployees()
        }
    }

    fun deleteNew(new: New) {
        viewModelScope.launch {
            DataSource.deleteNew(new)
            uploadNews()
        }
    }

    fun deleteEmployee(employee: Employee) {
        viewModelScope.launch {
            DataSource.deleteEmployee(employee)
            uploadEmployees()
        }
    }

    fun uploadEmployees() {

        pendingEmployees = true
        viewModelScope.launch {
            getAllEmployees().onSuccess {
                employees.clear()
                employees.addAll(it)
            }
            pendingEmployees = false
            refreshEmployees = false
        }
    }
}