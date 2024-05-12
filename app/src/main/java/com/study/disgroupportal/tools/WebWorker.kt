package com.study.disgroupportal.tools

import android.content.Context
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.net.Uri
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat.startActivity

object WebWorker {

    fun openInWeb(context: Context, uri: String) {
        try {
            openInWeb(context, Uri.parse(uri))
        } catch (e: Exception) {
            e.stackTrace
        }
    }

    private fun openInWeb(context: Context, uri: Uri) {
        try {
            CustomTabsIntent.Builder().apply {
                setDefaultColorSchemeParams(tabColorScheme)
                setUrlBarHidingEnabled(true)
            }.build().run {
                intent.addFlags(FLAG_ACTIVITY_NO_HISTORY)
                intent.data = uri
                startActivity(context, intent, startAnimationBundle)
            }
        } catch (e: Exception) {
            e.stackTrace
        }
    }

    private val tabColorScheme
        get() = CustomTabColorSchemeParams.Builder().build()
}