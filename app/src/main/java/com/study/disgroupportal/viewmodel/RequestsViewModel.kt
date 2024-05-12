package com.study.disgroupportal.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.study.disgroupportal.data.DataSource
import com.study.disgroupportal.model.employee.Employee
import com.study.disgroupportal.model.employee.UserRole.ADMIN
import com.study.disgroupportal.model.requests.Request
import com.study.disgroupportal.model.requests.RequestStatus
import com.study.disgroupportal.model.requests.RequestTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RequestsViewModel(
    application: Application,
) : AndroidViewModel(application) {

    var firstUploadRequests by mutableStateOf(true)
    var pendingRequests by mutableStateOf(false)
    var refreshRequests by mutableStateOf(false)

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

    fun clearVm() {
        clearRequests()
        clearFilters()
        firstUploadRequests = false
        refreshRequests = false
        pendingRequests = false
    }

    fun clearFilters() {
        _requestThemeFilters.value = emptyList()
        _requestStatusFilters.value = emptyList()
    }

    fun clearRequests() {
        _requests.value = emptyList()
    }

    fun uploadRequests(user: Employee?) {
        pendingRequests = true
        firstUploadRequests = false
        viewModelScope.launch {
            val list = mutableStateListOf<Request>()
            when (user?.role) {
                ADMIN -> DataSource.getAllRequests()
                    .onSuccess { list.addAll(it) }

                else -> DataSource.getUserRequests(user?.id)
                    .onSuccess { list.addAll(it) }
            }
            _requests.value = list
            pendingRequests = false
            refreshRequests = false
        }
    }

    fun answerRequest(user: Employee?, request: Request) {
        viewModelScope.launch {
            DataSource.editRequest(request)
            uploadRequests(user)
        }
    }

    fun addRequest(user: Employee?, request: Request) {
        viewModelScope.launch {
            DataSource.addRequest(request)
            uploadRequests(user)
        }
    }

    fun deleteRequest(user: Employee?, request: Request) {
        viewModelScope.launch {
            DataSource.deleteRequest(request)
            uploadRequests(user)
        }
    }
}