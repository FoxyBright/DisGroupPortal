package com.study.disgroupportal.view.portal.employee

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize.Max
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.study.disgroupportal.model.employee.Employee
import com.study.disgroupportal.view.components.WhiteAbsolutelyColor

@Composable
fun EmployeeItem(
    employee: Employee,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        colors = cardColors(WhiteAbsolutelyColor),
        elevation = cardElevation(2.dp),
        modifier = modifier.height(Max),
        onClick = onClick
    ) {
        Row(
            verticalAlignment = CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            EmployeeAvatar(employee, size = 80.dp)

            Spacer(Modifier.width(8.dp))

            Column {
                Text(
                    text = employee.name,
                    fontWeight = Medium,
                    fontSize = 18.sp,
                    color = DarkGray
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = employee.post,
                    fontWeight = Normal,
                    fontSize = 14.sp,
                    color = Gray
                )
            }
        }
    }
}