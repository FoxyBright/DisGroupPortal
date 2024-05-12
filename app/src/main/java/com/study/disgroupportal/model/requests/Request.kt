package com.study.disgroupportal.model.requests

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.study.disgroupportal.DisGroupPortalApp.Companion.curTime
import com.study.disgroupportal.model.requests.RequestStatus.OPENED
import com.study.disgroupportal.model.requests.RequestTheme.TAX

@Entity(tableName = "requests")
data class Request(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val theme: RequestTheme,
    val problem: String,
    val authorId: String,
    val authorName: String,
    val status: RequestStatus,
    val answer: String,
    val date: Long,
) {
    constructor(
        authorId: String,
        authorName: String
    ) : this(
        theme = TAX,
        problem = "",
        authorId = authorId,
        authorName = authorName,
        status = OPENED,
        answer = "",
        date = curTime
    )
}
