package com.study.disgroupportal.view.portal.employee

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.style.TextAlign.Companion.End
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.study.disgroupportal.R
import com.study.disgroupportal.model.employee.EditEmployType.CONTACTS
import com.study.disgroupportal.model.employee.EditEmployType.DEPARTAMENT
import com.study.disgroupportal.model.employee.EditEmployType.DUTIES
import com.study.disgroupportal.model.employee.EditEmployType.HEADER
import com.study.disgroupportal.model.employee.Employee
import com.study.disgroupportal.model.employee.UserRole.ADMIN
import com.study.disgroupportal.tools.getViewModel
import com.study.disgroupportal.view.components.BlackColor
import com.study.disgroupportal.view.components.CyanColor
import com.study.disgroupportal.view.components.DefaultButton
import com.study.disgroupportal.view.components.GrayColor
import com.study.disgroupportal.view.components.RedColor
import com.study.disgroupportal.view.components.WhiteAbsolutelyColor
import com.study.disgroupportal.view.components.WhiteColor
import com.study.disgroupportal.view.portal.employee.edit.EditEmployeeDialog
import com.study.disgroupportal.view.profile.ContactsCard
import com.study.disgroupportal.view.profile.DepartamentCard
import com.study.disgroupportal.view.profile.DutiesCard
import com.study.disgroupportal.viewmodel.EmployeeViewModel
import com.study.disgroupportal.viewmodel.MainViewModel

@Composable
fun EmployeeInfoScreen(
    isAdd: Boolean = false,
    employee: Employee?,
    onBack: () -> Unit
) {
    val mainVm = getViewModel<MainViewModel>()
    val employeeVm = getViewModel<EmployeeViewModel>()

    var currentEmployee by remember(employee, isAdd) {
        mutableStateOf(if (isAdd) Employee() else employee)
    }

    val showEditDialog = remember {
        mutableStateOf(false)
    }

    var editDialogType by remember {
        mutableStateOf(HEADER)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(Modifier.height(30.dp))

        Card(
            colors = cardColors(WhiteAbsolutelyColor),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = cardElevation(2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp, 12.dp),
                horizontalArrangement = SpaceBetween,
                verticalAlignment = CenterVertically
            ) {
                currentEmployee?.let { user ->
                    EmployeeAvatar(
                        modifier = Modifier
                            .background(WhiteColor, CircleShape)
                            .border(3.dp, BlackColor, CircleShape),
                        employee = user,
                        size = 120.dp
                    )
                }

                Spacer(Modifier.width(12.dp))

                Column(Modifier.weight(1f)) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = currentEmployee?.name ?: "",
                        fontWeight = Medium,
                        color = GrayColor,
                        fontSize = 20.sp,
                        textAlign = End
                    )

                    Spacer(Modifier.height(6.dp))

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = currentEmployee?.post ?: "",
                        fontSize = 14.sp,
                        textAlign = End,
                        color = Gray
                    )
                }
            }

            if (mainVm.user?.role == ADMIN) {
                Spacer(Modifier.height(10.dp))

                DefaultButton(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = stringResource(R.string.edit),
                    textColor = WhiteColor,
                    color = BlackColor
                ) {
                    editDialogType = HEADER
                    showEditDialog.value = true
                }

                Spacer(Modifier.height(10.dp))
            }
        }

        Spacer(Modifier.height(20.dp))

        DepartamentCard(
            departament = currentEmployee?.departament,
            showEditButton = mainVm.user?.role == ADMIN
        ) {
            editDialogType = DEPARTAMENT
            showEditDialog.value = true
        }

        if (mainVm.user?.role == ADMIN ||
            !currentEmployee?.duties.isNullOrEmpty()
        ) {
            Spacer(Modifier.height(20.dp))

            DutiesCard(
                showEditButton = mainVm.user?.role == ADMIN,
                user = currentEmployee
            ) {
                editDialogType = DUTIES
                showEditDialog.value = true
            }
        }

        Spacer(Modifier.height(20.dp))

        ContactsCard(
            showEditButton = mainVm.user?.role == ADMIN,
            user = currentEmployee
        ) {
            editDialogType = CONTACTS
            showEditDialog.value = true
        }

        if (mainVm.user?.role == ADMIN) {
            Spacer(Modifier.height(20.dp))

            DefaultButton(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(
                    if (isAdd) R.string.add else R.string.delete_employee
                ),
                textColor = WhiteColor,
                color = if (isAdd) CyanColor else RedColor
            ) {
                currentEmployee?.let {
                    if (isAdd) {
                        employeeVm.addEmployee(it)
                    } else {
                        employeeVm.deleteEmployee(it)
                    }
                }
                onBack()
            }
        }

        Spacer(Modifier.height(20.dp))
    }

    EditEmployeeDialog(
        showDialog = showEditDialog,
        employee = currentEmployee,
        dismissRequestAction = {},
        type = editDialogType
    ) { newData ->
        employeeVm.editEmployee(newData)
        currentEmployee = newData
    }
}