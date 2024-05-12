package com.study.disgroupportal.view.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.study.disgroupportal.data.AppDatabase.Companion.initDatabase
import com.study.disgroupportal.tools.getViewModel
import com.study.disgroupportal.ui.theme.DisGroupPortalTheme
import com.study.disgroupportal.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController
    private lateinit var mainVm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDatabase()
        mainVm = getViewModel()
        setContent {
            DisGroupPortalTheme {
                navHostController = rememberNavController()
                MainScreen(navHostController)
            }
        }
    }
}
