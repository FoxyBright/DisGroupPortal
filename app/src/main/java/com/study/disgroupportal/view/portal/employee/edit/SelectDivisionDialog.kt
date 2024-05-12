package com.study.disgroupportal.view.portal.employee.edit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.study.disgroupportal.model.portal.Tile
import com.study.disgroupportal.view.components.BlackColor
import com.study.disgroupportal.view.components.TeaColor

@Composable
inline fun <reified T : Tile> SelectDialog(
    list: List<T>,
    title: String = "",
    crossinline onSelect: (T) -> Unit = {}
) {
    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            fontWeight = SemiBold,
            fontSize = 22.sp,
            color = TeaColor,
            text = title,
        )

        Spacer(Modifier.height(20.dp))

        LazyColumn(verticalArrangement = spacedBy(8.dp)) {
            itemsIndexed(list) { i, element ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(CircleShape)
                        .clickable { onSelect(element) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = element.title,
                        textAlign = Center,
                        fontWeight = Medium,
                        color = BlackColor,
                        fontSize = 18.sp,
                    )
                }

                Spacer(Modifier.height(8.dp))

                if (i < list.lastIndex) {
                    HorizontalDivider(color = LightGray)
                }
            }
        }
    }
}