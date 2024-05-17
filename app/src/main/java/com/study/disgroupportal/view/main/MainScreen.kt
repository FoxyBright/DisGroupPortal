package com.study.disgroupportal.view.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.study.disgroupportal.DisGroupPortalApp.Companion.curScreen
import com.study.disgroupportal.DisGroupPortalApp.Companion.startDest
import com.study.disgroupportal.model.navigation.Destination.LOGIN
import com.study.disgroupportal.tools.getViewModel
import com.study.disgroupportal.view.Navigation
import com.study.disgroupportal.view.components.WhiteColor
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
            .background(WhiteColor),
        bottomBar = {
            if (curScreen != LOGIN) {
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
