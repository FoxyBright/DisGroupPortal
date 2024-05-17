package com.study.disgroupportal.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.study.disgroupportal.model.employee.Employee
import com.study.disgroupportal.model.news.New
import com.study.disgroupportal.model.statement.Statement

@Dao
interface Dao {

    /// Новости ///////////////////////////////////////////////

    @Query("SELECT * FROM news")
    suspend fun getNews(): List<New>

    @Update
    suspend fun editNew(value: New)

    @Delete
    suspend fun deleteNew(value: New)

    @Insert(onConflict = REPLACE)
    suspend fun addNew(value: New)

    @Query("DELETE FROM news")
    suspend fun clearAllNews()

    @Query("SELECT * FROM news WHERE id = :newId")
    suspend fun getNewById(newId: Long): List<New>

    /// Запросы ///////////////////////////////////////////////

    @Query("SELECT * FROM statement")
    suspend fun getRequests(): List<Statement>

    @Query("SELECT * FROM statement WHERE authorId = :userId")
    fun getUserStatements(userId: String): List<Statement>

    @Query("SELECT * FROM statement WHERE id = :statementId")
    suspend fun getStatementById(statementId: Long): List<Statement>

    @Query("DELETE FROM statement")
    suspend fun clearAllStatements()

    @Insert(onConflict = REPLACE)
    suspend fun addStatement(value: Statement)

    @Update
    suspend fun editStatement(value: Statement)

    @Delete
    suspend fun deleteStatement(value: Statement)

    /// Сотрудники ///////////////////////////////////////////////

    @Query("SELECT * FROM employee")
    suspend fun getEmployees(): List<Employee>

    @Query("DELETE FROM employee")
    suspend fun clearAllEmployees()

    @Insert(onConflict = REPLACE)
    suspend fun addEmployee(value: Employee)

    @Update
    suspend fun editEmployee(value: Employee)

    @Delete
    suspend fun deleteEmployee(value: Employee)
}