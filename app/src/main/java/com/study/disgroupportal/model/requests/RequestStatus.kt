package com.study.disgroupportal.model.requests

import androidx.compose.ui.graphics.Color
import com.study.disgroupportal.view.components.GreenColor
import com.study.disgroupportal.view.components.RedColor
import com.study.disgroupportal.view.components.YellowColor

enum class RequestStatus(val color: Color) {
    OPENED(YellowColor),
    CANCELED(RedColor),
    CLOSED(GreenColor)
}