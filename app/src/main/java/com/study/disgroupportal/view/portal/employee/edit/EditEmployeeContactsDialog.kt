package com.study.disgroupportal.view.portal.employee.edit

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.study.disgroupportal.R
import com.study.disgroupportal.model.employee.Employee
import com.study.disgroupportal.view.components.BlackColor
import com.study.disgroupportal.view.components.DefaultButton
import com.study.disgroupportal.view.components.GrayColor
import com.study.disgroupportal.view.components.WhiteColor

@Composable
fun EditEmployeeContactsDialog(
    employee: Employee,
    onCancelClick: () -> Unit = {},
    onSaveClick: (Employee) -> Unit = {}
) {
    val phone = remember(employee) {
        mutableStateOf(employee.phone)
    }

    val email = remember(employee) {
        mutableStateOf(employee.email)
    }

    Text(
        text = stringResource(R.string.edit_employee),
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
    )

    Spacer(Modifier.height(20.dp))

    Row(
        verticalAlignment = CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = ImageVector
                .vectorResource(R.drawable.ic_email),
            modifier = Modifier.size(24.dp),
            contentDescription = null,
            tint = GrayColor,
        )

        Spacer(Modifier.width(10.dp))

        EditTextField(
            placeholder = stringResource(R.string.none),
            value = email
        )
    }

    Spacer(Modifier.height(20.dp))

    Row(
        verticalAlignment = CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = ImageVector
                .vectorResource(R.drawable.ic_phone),
            modifier = Modifier.size(24.dp),
            contentDescription = null,
            tint = GrayColor,
        )

        Spacer(Modifier.width(10.dp))

        EditTextField(
            placeholder = stringResource(R.string.none),
            value = phone
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
            color = Color.LightGray
        )

        Spacer(Modifier.width(8.dp))

        DefaultButton(
            modifier = Modifier.weight(1f),
            text = stringResource(R.string.save),
            textColor = WhiteColor,
            color = BlackColor,
        ) {
            onSaveClick(
                employee.copy(
                    email = email.value,
                    phone = phone.value
                )
            )
        }
    }
}