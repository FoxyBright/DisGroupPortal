package com.study.disgroupportal.model.statement

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.study.disgroupportal.DisGroupPortalApp.Companion.curTime
import com.study.disgroupportal.model.statement.StatementStatus.OPENED
import com.study.disgroupportal.model.statement.StatementTheme.UNPAID_LEAVE

@Entity(tableName = "statement")
data class Statement(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val theme: StatementTheme = UNPAID_LEAVE,
    val content: String = "",
    val authorId: String = "",
    val authorName: String = "",
    val status: StatementStatus = OPENED,
    val answer: String? = null,
    val answerAuthor: String? = null,
    val dateStart: Long = curTime,
    val dateEnd: Long = 0,
    val investmentPath: String? = null
)
