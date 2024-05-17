package com.study.disgroupportal.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.study.disgroupportal.data.DataSource
import com.study.disgroupportal.model.employee.Employee
import com.study.disgroupportal.model.employee.UserRole.ADMIN
import com.study.disgroupportal.model.statement.Statement
import kotlinx.coroutines.launch

class StatementViewModel(
    application: Application,
) : AndroidViewModel(application) {
    val statements = mutableStateListOf<Statement>()

    fun uploadStatements(user: Employee?) {
        viewModelScope.launch {
            val list = mutableStateListOf<Statement>()
            when (user?.role) {
                ADMIN -> DataSource.getAllStatements()
                    .onSuccess { list.addAll(it) }

                else -> DataSource.getUserRequests(user?.id)
                    .onSuccess { list.addAll(it) }
            }
            statements.clear()
            statements.addAll(list)
        }
    }

    fun answerStatement(user: Employee?, statement: Statement) {
        viewModelScope.launch {
            DataSource.editStatement(statement)
            uploadStatements(user)
        }
    }

    fun addStatement(user: Employee?, statement: Statement) {
        viewModelScope.launch {
            DataSource.addStatement(statement)
            uploadStatements(user)
        }
    }

    fun deleteStatement(user: Employee?, statement: Statement) {
        viewModelScope.launch {
            DataSource.deleteStatement(statement)
            uploadStatements(user)
        }
    }
}