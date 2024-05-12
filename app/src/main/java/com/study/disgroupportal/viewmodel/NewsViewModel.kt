package com.study.disgroupportal.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.study.disgroupportal.data.DataSource
import com.study.disgroupportal.model.news.New
import kotlinx.coroutines.launch

class NewsViewModel(
    application: Application
) : AndroidViewModel(application) {

    val news = mutableStateListOf<New>()

    var pendingNews by mutableStateOf(false)
    var refreshNews by mutableStateOf(false)

    fun uploadNews() {
        pendingNews = true
        viewModelScope.launch {
            DataSource.getNews().onSuccess {
                news.clear()
                news.addAll(it)
            }
            pendingNews = false
            refreshNews = false
        }
    }

    fun clearNewVm() {
        news.clear()
        pendingNews = false
        refreshNews = false
    }

    fun deleteNew(new: New) {
        viewModelScope.launch {
            DataSource.deleteNew(new)
            uploadNews()
        }
    }

    fun addNew(new: New) {
        viewModelScope.launch {
            DataSource.addNew(new)
            uploadNews()
        }
    }
}