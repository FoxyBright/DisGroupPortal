package com.study.disgroupportal.model.employee

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.study.disgroupportal.model.portal.Departament
import com.study.disgroupportal.model.employee.UserRole.USER

@Entity(tableName = "employee")
data class Employee(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val departament: Departament? = null,
    val avatarUrl: String = "",
    val name: String = "",
    val post: String = "",
    val password: String? = null,
    val avatarPath: String = "",
    val login: String? = null,
    val role: UserRole = USER,
//    val duty: Duty = Duty(),
    val email: String = "",
    val phone: String = ""
)