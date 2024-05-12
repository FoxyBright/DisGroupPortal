package com.study.disgroupportal.view.portal.employee.edit

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.study.disgroupportal.R
import com.study.disgroupportal.model.employee.Employee
import com.study.disgroupportal.model.employee.UserRole
import com.study.disgroupportal.tools.getViewModel
import com.study.disgroupportal.tools.neededStoragePermissions
import com.study.disgroupportal.tools.openGallery
import com.study.disgroupportal.tools.showToast
import com.study.disgroupportal.view.components.BlackColor
import com.study.disgroupportal.view.components.DefaultButton
import com.study.disgroupportal.view.components.WhiteColor
import com.study.disgroupportal.view.portal.employee.EmployeeAvatar
import com.study.disgroupportal.viewmodel.MainViewModel

@Composable
@OptIn(ExperimentalPermissionsApi::class)
fun EditHeaderDialog(
    employee: Employee,
    isAdmin: Boolean = false,
    onCancelClick: () -> Unit = {},
    onSaveClick: (Employee) -> Unit = {}
) {
    val context = LocalContext.current

    val name = remember(employee) {
        mutableStateOf(employee.name)
    }

    val post = remember(employee) {
        mutableStateOf(employee.post)
    }

    Text(
        text = stringResource(R.string.edit_employee),
        fontWeight = SemiBold,
        fontSize = 20.sp,
    )

    Spacer(Modifier.height(20.dp))

    var avatarPath by remember(employee) {
        mutableStateOf(employee.avatarPath)
    }

    val imagePicker = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri -> uri?.let { avatarPath = it.toString() } }

    val permissionStorageState = rememberMultiplePermissionsState(
        permissions = neededStoragePermissions
    ) { results -> if (results.all { it.value }) imagePicker.launch("image/*") }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Center
    ) {
        EmployeeAvatar(
            employee = remember(employee, avatarPath) {
                mutableStateOf(employee.copy(avatarPath = avatarPath))
            }.value,
            modifier = Modifier
                .background(WhiteColor, CircleShape)
                .border(3.dp, BlackColor, CircleShape),
            size = 100.dp
        ) {
            permissionStorageState.openGallery(
                imagePicker = imagePicker,
                context = context
            )
        }
    }

    Spacer(Modifier.height(20.dp))

    EditTextField(
        placeholder = stringResource(R.string.edit_name_placeholder),
        value = name
    )

    if (isAdmin) {
        Spacer(Modifier.height(20.dp))
        EditTextField(
            placeholder = stringResource(R.string.edit_post_placeholder),
            value = post
        )
    }

    Spacer(Modifier.height(20.dp))

    Row(
        verticalAlignment = CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        DefaultButton(
            modifier = Modifier.weight(1f),
            text = stringResource(R.string.cancel),
            onClick = onCancelClick,
            textColor = WhiteColor,
            color = LightGray
        )

        Spacer(Modifier.width(8.dp))

        DefaultButton(
            modifier = Modifier.weight(1f),
            text = stringResource(R.string.save),
            textColor = WhiteColor,
            color = BlackColor,
        ) {
            if (name.value.isBlank()) {
                context.showToast(context.getString(R.string.edit_name_err))
                return@DefaultButton
            }
            if (post.value.isBlank()) {
                context.showToast(context.getString(R.string.edit_post_err))
                return@DefaultButton
            }

            onSaveClick(
                employee.copy(
                    avatarPath = avatarPath,
                    post = post.value,
                    name = name.value
                )
            )
        }
    }
}
