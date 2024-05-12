package com.study.disgroupportal.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.study.disgroupportal.model.navigation.Destination
import com.study.disgroupportal.model.navigation.Destination.ADD_NEW
import com.study.disgroupportal.model.navigation.Destination.ADD_REQUEST
import com.study.disgroupportal.model.navigation.Destination.LOGIN
import com.study.disgroupportal.model.navigation.Destination.NEWS
import com.study.disgroupportal.model.navigation.Destination.NEW_INFO
import com.study.disgroupportal.model.navigation.Destination.PORTAl
import com.study.disgroupportal.model.navigation.Destination.PROFILE
import com.study.disgroupportal.model.navigation.Destination.REQUESTS
import com.study.disgroupportal.model.navigation.Destination.REQUEST_INFO
import com.study.disgroupportal.model.navigation.DestinationArg.NEW_INFO_ARG
import com.study.disgroupportal.model.navigation.DestinationArg.REQUEST_INFO_ARG
import com.study.disgroupportal.tools.Navigation.argScreen
import com.study.disgroupportal.tools.Navigation.screen
import com.study.disgroupportal.view.login.LoginScreen
import com.study.disgroupportal.view.news.AddNewScreen
import com.study.disgroupportal.view.news.NewInfoScreen
import com.study.disgroupportal.view.news.NewsScreen
import com.study.disgroupportal.view.portal.PortalScreen
import com.study.disgroupportal.view.profile.ProfileScreen
import com.study.disgroupportal.view.requests.AddRequestScreen
import com.study.disgroupportal.view.requests.RequestInfoScreen
import com.study.disgroupportal.view.requests.RequestsScreen

@Composable
fun Navigation(
    navHostController: NavHostController,
    startDestination: Destination,
    modifier: Modifier = Modifier,
) {
    NavHost(
        startDestination = startDestination.route,
        navController = navHostController,
        modifier = modifier.fillMaxSize()
    ) {
        screen(LOGIN) { LoginScreen(navHostController) }

        screen(NEWS) { NewsScreen(navHostController) }
        argScreen(NEW_INFO, NEW_INFO_ARG) { NewInfoScreen(navHostController, it) }
        argScreen(ADD_NEW, NEW_INFO_ARG) { AddNewScreen(navHostController, it) }

        screen(PORTAl) { PortalScreen() }

        screen(PROFILE) { ProfileScreen(navHostController) }

        argScreen(REQUEST_INFO, REQUEST_INFO_ARG) { RequestInfoScreen(navHostController, it) }
        argScreen(ADD_REQUEST, REQUEST_INFO_ARG) { AddRequestScreen(navHostController, it) }
        screen(REQUESTS) { RequestsScreen(navHostController) }
    }
}