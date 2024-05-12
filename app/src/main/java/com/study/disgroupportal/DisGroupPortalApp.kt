package com.study.disgroupportal

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.study.disgroupportal.model.navigation.Destination
import com.study.disgroupportal.model.navigation.Destination.LOGIN
import com.study.disgroupportal.model.navigation.Destination.PORTAl
import com.study.disgroupportal.tools.SharedPrefs
import java.util.Calendar

class DisGroupPortalApp: Application() {
    
    override fun onCreate() {
        super.onCreate()
        prefs = SharedPrefs(applicationContext)
        instance = this
        startDest = if (prefs.hasToken) PORTAl else LOGIN
        curScreen = startDest
    }
    
    companion object {
        
        lateinit var startDest: Destination
        lateinit var instance: Application
        lateinit var prefs: SharedPrefs
        
        val curTime get() = Calendar.getInstance().time.time
        var curScreen by mutableStateOf(LOGIN)
    }
}