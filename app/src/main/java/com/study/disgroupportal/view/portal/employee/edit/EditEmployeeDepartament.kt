package com.study.disgroupportal.view.portal.employee.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.study.disgroupportal.R
import com.study.disgroupportal.model.employee.Employee
import com.study.disgroupportal.model.portal.Departament
import com.study.disgroupportal.model.portal.Departament.Companion.getListByDivision
import com.study.disgroupportal.model.portal.Division
import com.study.disgroupportal.model.portal.Tile
import com.study.disgroupportal.view.components.BlackColor
import com.study.disgroupportal.view.components.CyanColor
import com.study.disgroupportal.view.components.DefaultButton
import com.study.disgroupportal.view.components.WhiteAbsolutelyColor
import com.study.disgroupportal.view.components.WhiteColor
import com.study.disgroupportal.view.profile.DepartamentRow

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun EditEmployeeDepartament(
    employee: Employee,
    onCancelClick: () -> Unit = {},
    onSaveClick: (Employee) -> Unit = {}
) {
    val showSelectDialog = remember {
        mutableStateOf(false)
    }

    val departament = remember(employee) {
        mutableStateOf(employee.departament)
    }

    var division by remember {
        mutableStateOf<Division?>(null)
    }

    Text(
        text = stringResource(R.string.edit_employee),
        fontWeight = SemiBold,
        fontSize = 20.sp,
    )

    Spacer(Modifier.height(20.dp))

    DepartamentRow(
        icon = R.drawable.ic_division,
        value = departament.value?.division?.title
            ?.ifBlank { stringResource(R.string.none) }
            ?: stringResource(R.string.none)
    )

    HorizontalDivider(
        modifier = Modifier.padding(16.dp, 6.dp),
        color = LightGray
    )

    DepartamentRow(
        icon = R.drawable.ic_departament,
        value = departament.value?.title
            ?.ifBlank { stringResource(R.string.none) }
            ?: stringResource(R.string.none)
    )

    Spacer(Modifier.height(20.dp))

    DefaultButton(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(R.string.select),
        textColor = WhiteColor,
        color = CyanColor
    ) {
        showSelectDialog.value = true
    }

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
            onSaveClick(
                employee.copy(departament = departament.value)
            )
        }
    }

    if (showSelectDialog.value) {
        BasicAlertDialog(
            onDismissRequest = {
                showSelectDialog.value = false
                division = null
            },
        ) {

            val list = remember(division) {
                val list = division
                    ?.let { getListByDivision(it) }
                    ?: run { Division.entries }
                list.toMutableStateList()
            }

            Box(
                modifier = Modifier
                    .background(
                        shape = RoundedCornerShape(12.dp),
                        color = WhiteAbsolutelyColor
                    )
                    .padding(16.dp)
            ) {
                SelectDialog(
                    list = list,
                    title = stringResource(
                        if (division == null) R.string.select_division else R.string.select_departament
                    )
                ) { tile: Tile ->
                    division?.let {
                        departament.value = tile as? Departament
                        division = null
                        showSelectDialog.value = false
                    } ?: run {
                        division = tile as? Division
                    }
                }
            }
        }
    }
}