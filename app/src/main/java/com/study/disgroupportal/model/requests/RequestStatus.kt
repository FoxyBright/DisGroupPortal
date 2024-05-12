package com.study.disgroupportal.model.requests

import androidx.compose.ui.graphics.Color
import com.study.disgroupportal.ui.theme.EcologyColor
import com.study.disgroupportal.ui.theme.HealthColor
import com.study.disgroupportal.ui.theme.TransportColor

enum class RequestStatus(val color: Color) {
    OPENED(TransportColor),
    CANCELED(HealthColor),
    CLOSED(EcologyColor)
}