package com.study.disgroupportal.model.employee

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.study.disgroupportal.model.employee.UserRole.USER
import com.study.disgroupportal.model.portal.Departament
import java.util.stream.Collectors


@Entity(tableName = "employee")
@TypeConverters(ListConverter::class)
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
    val duties: List<String> = emptyList(),
    val email: String = "",
    val phone: String = ""
)

class ListConverter {
    @TypeConverter
    fun fromHobbies(hobbies: List<String>): String {
        return hobbies.stream().collect(Collectors.joining(","))
    }

    @TypeConverter
    fun toHobbies(data: String): List<String> {
        return listOf(*data.split(",".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray())
    }
}