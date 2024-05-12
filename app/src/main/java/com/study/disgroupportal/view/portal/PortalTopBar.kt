package com.study.disgroupportal.view.portal

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize.Max
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.input.KeyboardCapitalization.Companion.Sentences
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.study.disgroupportal.R
import com.study.disgroupportal.tools.getViewModel
import com.study.disgroupportal.ui.theme.Background
import com.study.disgroupportal.ui.theme.CyanColor
import com.study.disgroupportal.ui.theme.GrayColor
import com.study.disgroupportal.ui.theme.TeaColor
import com.study.disgroupportal.viewmodel.MainViewModel

@Composable
fun PortalTopBar(
    focus: MutableState<Boolean>,
    title: String,
) {
    val mainVm = getViewModel<MainViewModel>()

    Column {
        Box(
            Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Black, TeaColor)
                    )
                )
                .padding(16.dp, 20.dp),
            Alignment.Center
        ) {
            Text(
                fontWeight = SemiBold,
                fontSize = 20.sp,
                color = White,
                text = title
            )
        }

        Spacer(Modifier.height(12.dp))

        SearchRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = mainVm.searchText,
            focus = focus
        )

        Spacer(Modifier.height(12.dp))
    }
}

@Composable
private fun SearchRow(
    value: MutableState<String>,
    focus: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(Max),
        border = BorderStroke(
            width = 2.dp,
            color = when {
                focus.value -> CyanColor
                else -> Gray
            },
        ),
        elevation = cardElevation(1.dp),
        colors = cardColors(Background),
        shape = CircleShape
    ) {
        BasicTextField(
            onValueChange = { value.value = it },
            value = value.value,
            textStyle = TextStyle(
                fontWeight = Medium,
                color = GrayColor,
                fontSize = 18.sp
            ),
            singleLine = true,
            maxLines = 1,
            cursorBrush = SolidColor(CyanColor),
            modifier = Modifier
                .fillMaxWidth()
                .height(Max)
                .padding(18.dp, 8.dp)
                .onFocusChanged { focus.value = it.isFocused },
            keyboardOptions = KeyboardOptions(
                capitalization = Sentences,
                keyboardType = Text
            )
        ) { innerText ->
            if (value.value.isBlank()) {
                Text(
                    text = stringResource(R.string.search),
                    fontSize = 16.sp,
                    color = Gray
                )
            }
            innerText()
        }
    }
}