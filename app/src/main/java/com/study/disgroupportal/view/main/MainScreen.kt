package com.study.disgroupportal.view.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.SpaceAround
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.study.disgroupportal.DisGroupPortalApp.Companion.curScreen
import com.study.disgroupportal.DisGroupPortalApp.Companion.startDest
import com.study.disgroupportal.R
import com.study.disgroupportal.model.navigation.Destination
import com.study.disgroupportal.model.navigation.Destination.NEWS
import com.study.disgroupportal.model.navigation.Destination.PORTAl
import com.study.disgroupportal.model.navigation.Destination.PROFILE
import com.study.disgroupportal.tools.Navigation.navigateTo
import com.study.disgroupportal.tools.getViewModel
import com.study.disgroupportal.ui.theme.Background
import com.study.disgroupportal.ui.theme.CyanColor
import com.study.disgroupportal.view.Navigation
import com.study.disgroupportal.viewmodel.MainViewModel

@Composable
fun MainScreen(navHostController: NavHostController) {
    val mainVm = getViewModel<MainViewModel>()

    LaunchedEffect(Unit) {
        mainVm.user ?: mainVm.updateUser()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        bottomBar = {
            if (curScreen.showBottomBar) {
                MainBottomBar(navHostController)
            }
        }
    ) { paddings ->
        Navigation(
            navHostController = navHostController,
            startDestination = startDest,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings)
        )
    }
}

private enum class BottomButtons(
    @DrawableRes val icon: Int = 0,
    @StringRes val label: Int = 0,
    val dest: Destination,
) {
    NEWS_BT(
        icon = R.drawable.ic_news,
        label = R.string.news,
        dest = NEWS
    ),
    MAIN(
        icon = R.drawable.ic_home,
        label = R.string.main_page,
        dest = PORTAl
    ),
    PROFILE_BT(
        icon = R.drawable.ic_profile,
        label = R.string.profile,
        dest = PROFILE
    )
}

@Composable
private fun MainBottomBar(navHostController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Black),
        verticalAlignment = CenterVertically,
        horizontalArrangement = SpaceAround
    ) {
        BottomButtons.entries.forEach { button ->
            val isSelected by remember(curScreen, button) {
                mutableStateOf(curScreen == button.dest)
            }
            Card(
                onClick = { navHostController.navigateTo(button.dest) },
                colors = cardColors(Transparent),
                elevation = cardElevation(0.dp),
                shape = RectangleShape,
                modifier = Modifier
                    .height(56.dp)
                    .weight(1f)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    MainBottomBarItem(
                        isSelected = isSelected,
                        text = button.label,
                        icon = button.icon,
                    )
                }
            }
        }
    }
}

@Composable
private fun MainBottomBarItem(
    @DrawableRes icon: Int,
    @StringRes text: Int,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
) {
    val color by animateColorAsState(
        targetValue = if (isSelected) CyanColor else LightGray,
        animationSpec = tween(200), label = ""
    )
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .padding(top = 5.dp)
                .size(26.dp)
                .offset {
                    IntOffset(0, if (isSelected) -2 else 0)
                },
            tint = color
        )
        Text(
            fontSize = if (isSelected) 12.sp else 10.sp,
            text = stringResource(text),
            fontWeight = Medium,
            color = color
        )
    }
}
