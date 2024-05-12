package com.study.disgroupportal.model.employee

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.study.disgroupportal.model.employee.UserRole.USER
import com.study.disgroupportal.model.portal.Departament
import java.util.UUID
import java.util.stream.Collectors.joining


@Entity(tableName = "employee")
@TypeConverters(Converter::class)
data class Employee(
    @PrimaryKey(autoGenerate = false)
    val id: String = UUID.randomUUID().toString(),
    val departament: Departament? = null,
    val avatarUrl: String = "",
    val name: String = "",
    val post: String = "",
    val password: String? = null,
    val avatarPath: String = "",
    val login: String? = null,
    val role: UserRole = USER,
    val duties: List<String> = listOf("", "", "", "", ""),
    val email: String = "",
    val phone: String = ""
)

class Converter {
    @TypeConverter
    fun fromDuties(duties: List<String>): String = duties
        .stream().collect(joining(","))

    @TypeConverter
    fun toDuties(data: String) = listOf(
        *data.split(",".toRegex())
            .dropLastWhile { it.isEmpty() }
            .toTypedArray())
}