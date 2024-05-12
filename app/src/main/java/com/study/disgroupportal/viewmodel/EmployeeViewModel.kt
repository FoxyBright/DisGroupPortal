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
import com.study.disgroupportal.model.portal.Departament
import com.study.disgroupportal.model.portal.Division
import kotlinx.coroutines.launch

class EmployeeViewModel(
    application: Application,
) : AndroidViewModel(application) {

    val employees = mutableStateListOf<Employee>()

    val searchText = mutableStateOf("")

    var selectedDepartament by mutableStateOf<Departament?>(null)
    var selectedEmployee by mutableStateOf<Employee?>(null)
    var selectedDivision by mutableStateOf<Division?>(null)
    var isAddEmployee by mutableStateOf(false)

    fun clearVm() {
        employees.clear()
        searchText.value = ""
        selectedDepartament = null
        selectedEmployee = null
        selectedDivision = null
        isAddEmployee = false
    }

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