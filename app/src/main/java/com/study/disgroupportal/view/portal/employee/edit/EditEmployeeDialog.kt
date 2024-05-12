package com.study.disgroupportal.view.portal.employee.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.study.disgroupportal.model.employee.EditEmployType
import com.study.disgroupportal.model.employee.EditEmployType.CONTACTS
import com.study.disgroupportal.model.employee.EditEmployType.DUTIES
import com.study.disgroupportal.model.employee.EditEmployType.HEADER
import com.study.disgroupportal.model.employee.Employee
import com.study.disgroupportal.model.employee.UserRole.ADMIN
import com.study.disgroupportal.tools.getViewModel
import com.study.disgroupportal.view.components.WhiteAbsolutelyColor
import com.study.disgroupportal.viewmodel.EmployeeViewModel
import com.study.disgroupportal.viewmodel.MainViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun EditEmployeeDialog(
    employee: MutableState<Employee?>,
    showDialog: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    type: EditEmployType,
    dismissRequestAction: () -> Unit = {}
) {
    if (employee.value == null) return

    val employeeVm = getViewModel<EmployeeViewModel>()
    val mainVm = getViewModel<MainViewModel>()

    if (showDialog.value) {
        BasicAlertDialog(
            onDismissRequest = dismissRequestAction,
            modifier = modifier
        ) {
            Column(
                modifier = Modifier
                    .background(
                        shape = RoundedCornerShape(12.dp),
                        color = WhiteAbsolutelyColor
                    )
                    .padding(16.dp)
            ) {
                when (type) {
                    CONTACTS -> EditEmployeeContactsDialog(
                        onCancelClick = { showDialog.value = false },
                        employee = employee.value!!
                    ) { newData ->
                        employeeVm.editEmployee(newData)
                        employee.value = newData
                        showDialog.value = false
                        mainVm.updateUser()
                    }

                    DUTIES -> EditEmployeeDutiesDialogs(
                        onCancelClick = { showDialog.value = false },
                        employee = employee.value!!
                    ) { newData ->
                        employeeVm.editEmployee(newData)
                        employee.value = newData
                        showDialog.value = false
                        mainVm.updateUser()
                    }

                    HEADER -> EditHeaderDialog(
                        onCancelClick = { showDialog.value = false },
                        isAdmin = mainVm.user?.role == ADMIN,
                        employee = employee.value!!
                    ) { newData ->
                        employeeVm.editEmployee(newData)
                        employee.value = newData
                        showDialog.value = false
                        mainVm.updateUser()
                    }
                }
            }
        }
    }
}