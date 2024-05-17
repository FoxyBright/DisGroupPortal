package com.study.disgroupportal.model.statement

import androidx.compose.ui.graphics.Color
import com.study.disgroupportal.R
import com.study.disgroupportal.view.components.GreenColor
import com.study.disgroupportal.view.components.RedColor
import com.study.disgroupportal.view.components.YellowColor

enum class StatementStatus(
    val color: Color,
    val label: Int
) {
    OPENED(YellowColor, R.string.opened_status),
    CANCELED(RedColor, R.string.canceled_status),
    CLOSED(GreenColor, R.string.closed_status)
}