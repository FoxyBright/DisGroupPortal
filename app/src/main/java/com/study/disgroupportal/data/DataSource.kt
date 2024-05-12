package com.study.disgroupportal.data

import com.study.disgroupportal.data.AppDatabase.Companion.dbDao
import com.study.disgroupportal.data.Presets.newsPresets
import com.study.disgroupportal.data.Presets.requestsPresets
import com.study.disgroupportal.data.Presets.employeesPresets
import com.study.disgroupportal.model.news.New
import com.study.disgroupportal.model.requests.Request
import com.study.disgroupportal.model.employee.Employee
import com.study.disgroupportal.model.employee.UserRole.ADMIN
import com.study.disgroupportal.tools.logE
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success

object DataSource {

    private const val LOG_TAG = "Data Source"

    suspend fun setDbPresets() = withContext(IO) {
        clearDb()
        requestsPresets.forEach { dbDao.addRequest(it) }
        employeesPresets.forEach { dbDao.addEmployee(it) }
        newsPresets.forEach { dbDao.addNew(it) }
    }

    suspend fun clearDb() = withContext(IO) {
        dbDao.clearAllRequests()
        dbDao.clearAllEmployees()
        dbDao.clearAllNews()
    }

    suspend fun getNews() = withContext(IO) {
        success(dbDao.getNews())
    }

    suspend fun loginInGosuslugi(
        login: String,
        password: String,
        isAdministrateMode: Boolean,
    ) = withContext(IO) {
        delay(1000L)
        employeesPresets.find { user ->
            if (isAdministrateMode) {
                user.role == ADMIN
            } else {
                login == user.login
            } && user.password == password
        }?.let { success(it) } ?: run {
            failure(Throwable("Неверный логин или пароль".logE(LOG_TAG)))
        }
    }

    suspend fun getUser(token: Long) = withContext(IO) {
        employeesPresets.find { it.id == token }?.let { success(it) } ?: run {
            failure(Throwable("Токен устарел, перепройдите авторизацию".logE(LOG_TAG)))
        }
    }

    suspend fun getNewById(id: Long) = withContext(IO) {
        dbDao.getNewById(id).firstOrNull()?.let { success(it) } ?: run {
            failure(Throwable("Не удалось найти новость по данному id".logE(LOG_TAG)))
        }
    }

    suspend fun getAllRequests() = withContext(IO) {
        success(dbDao.getRequests())
    }

    suspend fun getRequestById(requestId: Long) = withContext(IO) {
        dbDao.getRequestById(requestId).firstOrNull()?.let { success(it) } ?: run {
            failure(Throwable("Не удалось найти запрос по данному id".logE(LOG_TAG)))
        }
    }

    suspend fun getUserRequests(userId: Long?) = withContext(IO) {
        userId?.let { id ->
            success(dbDao.getUserRequests(id).sortedBy { it.date })
        } ?: run {
            failure(Throwable("id Пользователя указан как null".logE(LOG_TAG)))
        }
    }

    suspend fun editRequest(request: Request) = withContext(IO) {
        dbDao.editRequest(request)
    }

    suspend fun addRequest(request: Request) = withContext(IO) {
        dbDao.addRequest(request)
    }

    suspend fun addNew(new: New) = withContext(IO) {
        dbDao.addNew(new)
    }

    suspend fun addEmployee(employee: Employee) = withContext(IO) {
        dbDao.addEmployee(employee)
    }

    suspend fun deleteRequest(request: Request) = withContext(IO) {
        dbDao.deleteRequest(request)
    }

    suspend fun deleteNew(new: New) = withContext(IO) {
        dbDao.deleteNew(new)
    }

    suspend fun deleteEmployee(employee: Employee) = withContext(IO) {
        dbDao.deleteEmployee(employee)
    }

    suspend fun getAllEmployees() = withContext(IO) {
        success(dbDao.getEmployee())
    }

    suspend fun getEmployeeById(employeeId: Long) = withContext(IO) {
        dbDao.getEmployeeById(employeeId).firstOrNull()?.let { success(it) } ?: run {
            failure(Throwable("Не удалось найти служащего по данному id".logE(LOG_TAG)))
        }
    }
}