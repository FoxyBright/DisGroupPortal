package com.study.disgroupportal.view.main

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.study.disgroupportal.data.AppDatabase.Companion.initDatabase
import com.study.disgroupportal.tools.getViewModel
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
            setViewSettings()
            navHostController = rememberNavController()
            MainScreen(navHostController)
        }
    }
}

@Composable
private fun setViewSettings() {
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