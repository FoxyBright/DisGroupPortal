package com.study.disgroupportal.tools

import android.content.Context
import android.content.Context.MODE_PRIVATE


class SharedPrefs(context: Context) {

    companion object {

        private const val ID_TOKEN = "identity_token"
        const val PREFS_NAME = "shared_preferences"
    }

    private var prefs = context
        .getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

    fun saveToken(token: String) {
        prefs.edit().putString(ID_TOKEN, token).apply()
    }

    fun clearToken() {
        prefs.edit().putString(ID_TOKEN, "").apply()
    }

    val authToken: String get() = (prefs.getString(ID_TOKEN, "") ?: "")

    val hasToken: Boolean get() = authToken.isNotBlank()
}