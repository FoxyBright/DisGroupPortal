package com.study.disgroupportal.view.portal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.style.TextAlign.Companion.End
import androidx.compose.ui.text.style.TextAlign.Companion.Start
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.study.disgroupportal.model.portal.Tile
import com.study.disgroupportal.ui.theme.Background
import com.study.disgroupportal.ui.theme.CyanColor

@Composable
fun TileItem(
    tile: Tile,
    isHighlight: Boolean,
    onClick: (Tile) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .background(
                color = if (isHighlight) {
                    Black
                } else {
                    Background
                }
            )
            .clickable { onClick(tile) }
            .padding(16.dp, 24.dp)
    ) {
        if (!isHighlight) {
            TileImage(tile.image, Modifier.weight(1f))
            Spacer(Modifier.width(8.dp))
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1.5f),
            verticalArrangement = SpaceBetween
        ) {
            val alignment = remember(isHighlight) {
                if (isHighlight) Start else End
            }

            Text(
                modifier = Modifier.fillMaxWidth(),
                color = if (isHighlight) CyanColor else Black,
                fontWeight = FontWeight.SemiBold,
                textAlign = alignment,
                text = tile.title,
                fontSize = 16.sp
            )

            Text(
                color = if (isHighlight) Background else Black,
                modifier = Modifier.fillMaxWidth(),
                text = tile.description,
                textAlign = alignment,
                fontWeight = Medium,
                fontSize = 14.sp
            )
        }

        if (isHighlight) {
            Spacer(Modifier.width(8.dp))
            TileImage(tile.image, Modifier.weight(1f))
        }
    }
}

@Composable
private fun TileImage(
    img: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier.fillMaxHeight(),
        painter = painterResource(img),
        contentDescription = null,
    )
}