package com.study.disgroupportal.view.portal.employee.edit

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.study.disgroupportal.R
import com.study.disgroupportal.model.employee.Employee
import com.study.disgroupportal.view.components.BlackColor
import com.study.disgroupportal.view.components.DefaultButton
import com.study.disgroupportal.view.components.WhiteColor

@Composable
fun EditEmployeeDutiesDialogs(
    employee: Employee,
    onCancelClick: () -> Unit = {},
    onSaveClick: (Employee) -> Unit = {}
) {
    Text(
        text = stringResource(R.string.edit_employee),
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
    )

    Spacer(Modifier.height(20.dp))

    val duties = remember(employee) {
        employee.duties.map { mutableStateOf(it) }.toMutableStateList()
    }

    duties.forEach { duty ->
        EditTextField(
            placeholder = stringResource(R.string.none),
            value = duty
        )

        Spacer(Modifier.height(20.dp))
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
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
            onSaveClick(employee.copy(duties = duties.map { it.value }))
        }
    }
}