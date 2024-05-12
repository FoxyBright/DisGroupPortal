package com.study.disgroupportal.view.profile

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.study.disgroupportal.R
import com.study.disgroupportal.model.employee.UserRole.ADMIN
import com.study.disgroupportal.tools.getViewModel
import com.study.disgroupportal.view.components.BlackColor
import com.study.disgroupportal.view.components.DefaultButton
import com.study.disgroupportal.view.components.RedColor
import com.study.disgroupportal.view.components.WhiteColor
import com.study.disgroupportal.viewmodel.EmployeeViewModel
import com.study.disgroupportal.viewmodel.MainViewModel
import com.study.disgroupportal.viewmodel.NewsViewModel
import com.study.disgroupportal.viewmodel.RequestsViewModel

@Composable
fun DbSettingsCard(showDbSettings: MutableState<Boolean>) {
    val employeeVm = getViewModel<EmployeeViewModel>()
    val requestsVm = getViewModel<RequestsViewModel>()
    val mainVm = getViewModel<MainViewModel>()
    val newsVm = getViewModel<NewsViewModel>()

    Crossfade(
        targetState = showDbSettings.value
                && mainVm.user?.role == ADMIN,
        label = "Database control buttons animation"
    ) { showButtons ->
        if (showButtons) {
            Column {
                Spacer(Modifier.height(20.dp))

                DefaultButton(
                    text = stringResource(R.string.set_database_presets),
                    textColor = WhiteColor,
                    color = BlackColor
                ) {
                    mainVm.setDatabasePresets()
                    employeeVm.uploadEmployees()
                    requestsVm.uploadRequests(mainVm.user)
                    newsVm.uploadNews()
                    showDbSettings.value = false
                }

                Spacer(Modifier.height(10.dp))

                DefaultButton(
                    text = stringResource(R.string.clear_database),
                    textColor = WhiteColor,
                    color = RedColor
                ) {
                    mainVm.clearDatabase()
                    employeeVm.employees.clear()
                    newsVm.news.clear()
                    requestsVm.clearRequests()
                    requestsVm.clearFilters()
                    showDbSettings.value = false
                }
            }
        }
    }
}