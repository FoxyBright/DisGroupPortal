package com.study.disgroupportal.model.news

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.study.disgroupportal.DisGroupPortalApp.Companion.curTime

@Entity(tableName = "news")
data class New(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val date: Long,
    val title: String,
    val imageUrl: String,
    val link: String,
    val text: String,
    val imagePath: String = "",
) {
    constructor() : this(
        date = curTime,
        imageUrl = "",
        title = "",
        link = "",
        text = ""
    )
}