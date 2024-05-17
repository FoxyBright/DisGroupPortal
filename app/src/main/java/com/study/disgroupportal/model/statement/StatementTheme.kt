package com.study.disgroupportal.model.statement

import androidx.annotation.StringRes
import com.study.disgroupportal.R

enum class StatementTheme(
    @StringRes
    val label: Int,
) {
    UNPAID_LEAVE(R.string.unpaid_leave),
    PAID_LEAVE(R.string.paid_leave),
    DAY_OFF(R.string.day_off),
    MEDICAL(R.string.medical),
    DISMISSAL(R.string.dismissal),
    RECALCULATION_OF_SALARY(R.string.recalculation_of_salary),
}