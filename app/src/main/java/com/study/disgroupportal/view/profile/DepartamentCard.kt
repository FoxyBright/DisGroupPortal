package com.study.disgroupportal.view.profile

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.study.disgroupportal.R
import com.study.disgroupportal.model.portal.Departament
import com.study.disgroupportal.view.components.BlackColor
import com.study.disgroupportal.view.components.DefaultButton
import com.study.disgroupportal.view.components.GrayColor
import com.study.disgroupportal.view.components.WhiteAbsolutelyColor
import com.study.disgroupportal.view.components.WhiteColor

@Composable
fun DepartamentCard(
    departament: Departament?,
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

        DepartamentRow(
            icon = R.drawable.ic_division,
            value = departament?.division?.title
                ?.ifBlank { stringResource(R.string.none) }
                ?: stringResource(R.string.none)
        )

        HorizontalDivider(
            modifier = Modifier.padding(16.dp, 6.dp),
            color = Color.LightGray
        )

        DepartamentRow(
            icon = R.drawable.ic_departament,
            value = departament?.title
                ?.ifBlank { stringResource(R.string.none) }
                ?: stringResource(R.string.none)
        )

        if (showEditButton) {
            Spacer(Modifier.height(10.dp))

            DefaultButton(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(R.string.edit),
                textColor = WhiteColor,
                onClick = onEditClick,
                color = BlackColor
            )
        }

        Spacer(Modifier.height(10.dp))
    }
}

@Composable
fun DepartamentRow(
    @DrawableRes
    icon: Int,
    value: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Icon(
            imageVector = ImageVector
                .vectorResource(icon),
            modifier = Modifier.size(24.dp),
            contentDescription = null,
            tint = GrayColor,
        )

        Spacer(Modifier.width(12.dp))

        Text(
            fontWeight = Medium,
            color = GrayColor,
            fontSize = 18.sp,
            text = value
        )
    }
}