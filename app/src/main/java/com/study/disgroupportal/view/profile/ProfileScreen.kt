package com.study.disgroupportal.view.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush.Companion.verticalGradient
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.study.disgroupportal.BuildConfig.VERSION_NAME
import com.study.disgroupportal.DisGroupPortalApp.Companion.curScreen
import com.study.disgroupportal.R
import com.study.disgroupportal.model.employee.EditEmployType.CONTACTS
import com.study.disgroupportal.model.employee.EditEmployType.DEPARTAMENT
import com.study.disgroupportal.model.employee.EditEmployType.DUTIES
import com.study.disgroupportal.model.employee.EditEmployType.HEADER
import com.study.disgroupportal.model.employee.UserRole.ADMIN
import com.study.disgroupportal.model.navigation.Destination.PROFILE
import com.study.disgroupportal.tools.getViewModel
import com.study.disgroupportal.view.components.BlackColor
import com.study.disgroupportal.view.components.TeaColor
import com.study.disgroupportal.view.components.WhiteColor
import com.study.disgroupportal.view.portal.employee.edit.EditEmployeeDialog
import com.study.disgroupportal.viewmodel.EmployeeViewModel
import com.study.disgroupportal.viewmodel.MainViewModel

@Composable
fun ProfileScreen(navHostController: NavHostController) {
    val employeeVm = getViewModel<EmployeeViewModel>()
    val mainVm = getViewModel<MainViewModel>()

    LaunchedEffect(Unit) { curScreen = PROFILE }

    var editDialogType by remember {
        mutableStateOf(HEADER)
    }

    val showDbSettings = remember {
        mutableStateOf(false)
    }

    val showExitDialog = remember {
        mutableStateOf(false)
    }

    var enableSettings by remember {
        mutableStateOf(false)
    }

    val showEditDialog = remember {
        mutableStateOf(false)
    }

    Background()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = CenterHorizontally
    ) {
        Spacer(Modifier.height(100.dp))

        ProfileHeaderCard(
            onSettingsClick = { enableSettings = !enableSettings },
            onLogoutClick = { showExitDialog.value = true },
            settingsEnabled = enableSettings,
            showEditButton = enableSettings,
            user = mainVm.user,
            onEditClick = {
                editDialogType = HEADER
                showEditDialog.value = true
            }
        )

        Spacer(Modifier.height(20.dp))

        DepartamentCard(
            departament = mainVm.user?.departament,
            showEditButton = mainVm.user
                ?.role == ADMIN && enableSettings
        ) {
            editDialogType = DEPARTAMENT
            showEditDialog.value = true
        }

        if (!mainVm.user?.duties.isNullOrEmpty()) {
            Spacer(Modifier.height(20.dp))
            DutiesCard(
                showEditButton = mainVm.user
                    ?.role == ADMIN && enableSettings,
                user = mainVm.user
            ) {
                editDialogType = DUTIES
                showEditDialog.value = true
            }
        }

        Spacer(Modifier.height(20.dp))

        ContactsCard(
            showEditButton = enableSettings,
            user = mainVm.user
        ) {
            editDialogType = CONTACTS
            showEditDialog.value = true
        }

        DbSettingsCard(showDbSettings)

        Spacer(Modifier.weight(1f))

        Text(
            text = stringResource(R.string.version, VERSION_NAME),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
                .pointerInput(Unit) {
                    detectTapGestures(onLongPress = {
                        showDbSettings.apply { value = !value }
                    })
                },
            textAlign = TextAlign.Center,
            fontWeight = Medium,
            fontSize = 14.sp,
            color = Gray
        )

        Spacer(Modifier.height(70.dp))
    }

    ExitDialog(
        navHostController = navHostController,
        showErrorDialog = showExitDialog
    )

    EditEmployeeDialog(
        showDialog = showEditDialog,
        dismissRequestAction = {},
        employee = mainVm.user,
        type = editDialogType
    ) { newData ->
        employeeVm.editEmployee(newData)
        mainVm.user = newData
    }
}

@Composable
private fun Background() {
    Column(Modifier.background(WhiteColor)) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f)
                .background(
                    brush = verticalGradient(
                        listOf(TeaColor, BlackColor)
                    ),
                    shape = RoundedCornerShape(
                        bottomEnd = 12.dp,
                        bottomStart = 12.dp
                    )
                )
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(6f)
                .background(WhiteColor)
        )
    }
}