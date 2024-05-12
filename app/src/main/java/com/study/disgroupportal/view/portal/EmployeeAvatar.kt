package com.study.disgroupportal.view.portal

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import com.study.disgroupportal.R
import com.study.disgroupportal.model.employee.Employee
import com.study.disgroupportal.view.components.WhiteColor


@Composable
fun EmployeeAvatar(
    employee: Employee,
    size: Dp,
    modifier: Modifier = Modifier
) {
    when {
        employee.avatarPath.isNotBlank() -> Uri.parse(employee.avatarPath)
        employee.avatarUrl.isNotBlank() -> employee.avatarUrl
        else -> null
    }?.let { image ->
        AsyncImage(
            contentDescription = null,
            modifier = modifier
                .size(size)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            model = image
        )
    } ?: run {
        Box(
            modifier = modifier
                .size(size)
                .clip(CircleShape)
                .background(WhiteColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    R.drawable.profile_placeholder
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(size / 4 * 3)
                    .clip(CircleShape),
                tint = WhiteColor
            )
        }
    }
}