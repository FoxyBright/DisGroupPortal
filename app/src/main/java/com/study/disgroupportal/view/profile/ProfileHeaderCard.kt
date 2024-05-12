package com.study.disgroupportal.view.profile

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.study.disgroupportal.R
import com.study.disgroupportal.model.employee.Employee
import com.study.disgroupportal.view.components.BlackColor
import com.study.disgroupportal.view.components.CyanColor
import com.study.disgroupportal.view.components.DefaultButton
import com.study.disgroupportal.view.components.GrayColor
import com.study.disgroupportal.view.components.RedColor
import com.study.disgroupportal.view.components.WhiteAbsolutelyColor
import com.study.disgroupportal.view.components.WhiteColor
import com.study.disgroupportal.view.portal.employee.EmployeeAvatar


@Composable
fun ProfileHeaderCard(
    user: Employee?,
    settingsEnabled: Boolean = false,
    showEditButton: Boolean = false,
    onEditClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
) {
    val settingsAnimate = animateFloatAsState(
        targetValue = if (settingsEnabled) 180f else 0f,
        animationSpec = tween(1000),
        label = "settings animation"
    )

    Box {
        Card(
            colors = cardColors(WhiteAbsolutelyColor),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = cardElevation(2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .padding(horizontal = 24.dp),
                horizontalArrangement = SpaceBetween,
                verticalAlignment = CenterVertically
            ) {
                Icon(
                    tint = if (settingsEnabled) CyanColor else GrayColor,
                    imageVector = ImageVector
                        .vectorResource(R.drawable.ic_settings),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onSettingsClick() }
                        .graphicsLayer { rotationZ = settingsAnimate.value }
                )

                Icon(
                    imageVector = ImageVector
                        .vectorResource(R.drawable.ic_logout),
                    contentDescription = null,
                    tint = RedColor,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onLogoutClick() }
                )
            }

            Spacer(Modifier.height(20.dp))

            Text(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(horizontal = 16.dp),
                text = user?.name ?: " ",
                fontWeight = Medium,
                textAlign = Center,
                color = GrayColor,
                fontSize = 24.sp
            )

            Text(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(horizontal = 16.dp),
                text = user?.post ?: " ",
                textAlign = Center,
                fontSize = 14.sp,
                color = Gray
            )

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

            Spacer(Modifier.height(16.dp))
        }

        user?.let { employee ->
            EmployeeAvatar(
                employee = employee,
                size = 120.dp,
                modifier = Modifier
                    .offset(y = (-60).dp)
                    .background(WhiteColor, CircleShape)
                    .clip(CircleShape)
                    .align(TopCenter)
                    .border(3.dp, WhiteColor, CircleShape)
            )
        }
    }
}