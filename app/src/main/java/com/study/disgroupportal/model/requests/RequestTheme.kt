package com.study.disgroupportal.model.requests

import androidx.annotation.StringRes
import com.study.disgroupportal.R

enum class RequestTheme(
    @StringRes
    val label: Int,
) {
    
    TAX(R.string.tax),
    QUESTION(R.string.question),
    ARRANGEMENT(R.string.arrangement),
    DEDUCTIONS(R.string.deductions),
    FINE(R.string.fine)
}