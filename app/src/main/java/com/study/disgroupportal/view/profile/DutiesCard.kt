package com.study.disgroupportal.view.profile

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.study.disgroupportal.R
import com.study.disgroupportal.model.employee.Employee
import com.study.disgroupportal.view.components.BlackColor
import com.study.disgroupportal.view.components.DefaultButton
import com.study.disgroupportal.view.components.GrayColor
import com.study.disgroupportal.view.components.WhiteAbsolutelyColor
import com.study.disgroupportal.view.components.WhiteColor


@Composable
fun DutiesCard(
    user: Employee?,
    showEditButton: Boolean = false,
    onEditClick: () -> Unit = {}
) {
    Card(
        colors = cardColors(WhiteAbsolutelyColor),
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = cardElevation(2.dp)
    ) {
        Spacer(Modifier.height(16.dp))

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(R.string.duties),
            fontWeight = SemiBold,
            fontSize = 18.sp,
            color = GrayColor
        )

        Spacer(Modifier.height(10.dp))

        user?.duties?.forEachIndexed { i, duty ->
            DutyRow(duty, i != user.duties.lastIndex)
        } ?: run {
            repeat(5) {
                DutyRow(" ", it != 4)
            }
        }

        if (showEditButton) {
            Spacer(Modifier.height(10.dp))

            DefaultButton(
                text = stringResource(R.string.edit),
                modifier = Modifier.padding(horizontal = 16.dp),
                textColor = WhiteColor,
                onClick = onEditClick,
                color = BlackColor
            )
        }

        Spacer(Modifier.height(10.dp))
    }
}

@Composable
private fun DutyRow(
    text: String,
    addDivider: Boolean = true
) {
    Text(
        modifier = Modifier.padding(16.dp, 6.dp),
        fontSize = 16.sp,
        color = GrayColor,
        text = text
    )

    if (addDivider) {
        HorizontalDivider(color = LightGray)
    }
}