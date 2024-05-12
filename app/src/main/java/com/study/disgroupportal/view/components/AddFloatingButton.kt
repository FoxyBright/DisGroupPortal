package com.study.disgroupportal.view.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults.iconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddFloatingButton(onClick: () -> Unit) {
    IconButton(
        modifier = Modifier.size(42.dp),
        colors = iconButtonColors(BlackColor),
        onClick = onClick,
    ) {
        Icon(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp),
            imageVector = Default.Add,
            contentDescription = null,
            tint = WhiteColor
        )
    }
}