package com.study.disgroupportal.model.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.study.disgroupportal.R
import com.study.disgroupportal.model.navigation.Destination.NEWS
import com.study.disgroupportal.model.navigation.Destination.PORTAl
import com.study.disgroupportal.model.navigation.Destination.PROFILE
import com.study.disgroupportal.model.navigation.Destination.STATEMENTS

enum class BottomButtons(
    @DrawableRes val icon: Int = 0,
    @StringRes val label: Int = 0,
    val dest: Destination,
) {
    MAIN(
        icon = R.drawable.ic_home,
        label = R.string.main_page,
        dest = PORTAl
    ),
    STATEMENT(
        icon = R.drawable.ic_file,
        label = R.string.reporting,
        dest = STATEMENTS
    ),
    NEWS_BT(
        icon = R.drawable.ic_news,
        label = R.string.news,
        dest = NEWS
    ),
    PROFILE_BT(
        icon = R.drawable.ic_profile,
        label = R.string.profile,
        dest = PROFILE
    )
}