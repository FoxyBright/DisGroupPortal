package com.study.disgroupportal.view.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.study.disgroupportal.data.AppDatabase.Companion.initDatabase
import com.study.disgroupportal.model.navigation.Destination
import com.study.disgroupportal.model.navigation.DestinationArg
import com.study.disgroupportal.model.navigation.NavArgument
import com.study.disgroupportal.tools.Navigation.navigateTo
import com.study.disgroupportal.tools.SharedPrefs
import com.study.disgroupportal.tools.getViewModel
import com.study.disgroupportal.tools.logE
import com.study.disgroupportal.view.components.BlackColor
import com.study.disgroupportal.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController
    private lateinit var mainVm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDatabase()
        mainVm = getViewModel()

        setContent {
            SetViewSettings()
            navHostController = rememberNavController()
            LaunchedEffect(Unit) { deepLinkHandler(intent) }
            MainScreen(navHostController)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        deepLinkHandler(intent)
    }

    private fun deepLinkHandler(intent: Intent) {
        "check tokens".logE("loggy")
        if (!SharedPrefs(this).hasToken) return
        "is authorized".logE("loggy")
        "check id".logE("loggy")

        val statementId = intent.data
            ?.pathSegments
            ?.lastOrNull()
            ?.toLongOrNull()
            ?: return

        "id = $statementId".logE("loggy")
        "navigate".logE("loggy")
        navHostController.navigateTo(
            arg = NavArgument(
                argument = DestinationArg.STATEMENT_INFO_ARG,
                value = statementId
            ),
            dest = Destination.STATEMENT_INFO
        )
    }
}

@Composable
private fun SetViewSettings() {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = BlackColor.toArgb()
            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightStatusBars = false
        }
    }
}