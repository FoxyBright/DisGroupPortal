package com.study.disgroupportal.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.study.disgroupportal.data.DataSource
import com.study.disgroupportal.model.employee.Employee
import kotlinx.coroutines.launch

class EmployeeViewModel(
    application: Application,
) : AndroidViewModel(application) {

    val employees = mutableStateListOf<Employee>()

    val searchText = mutableStateOf("")

    fun addEmployee(employee: Employee) {
        viewModelScope.launch {
            DataSource.addEmployee(employee)
            uploadEmployees()
        }
    }

    fun deleteEmployee(employee: Employee) {
        viewModelScope.launch {
            DataSource.deleteEmployee(employee)
            uploadEmployees()
        }
    }

    fun editEmployee(employee: Employee) {
        viewModelScope.launch {
            DataSource.editEmployee(employee)
            uploadEmployees()
        }
    }

    fun uploadEmployees() {
        viewModelScope.launch {
            DataSource.getAllEmployees().onSuccess {
                employees.clear()
                employees.addAll(it)
            }
        }
    }
}