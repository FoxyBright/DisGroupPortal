package com.study.disgroupportal.view.portal.employee.edit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.input.KeyboardCapitalization.Companion.Sentences
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.study.disgroupportal.view.components.CyanColor
import com.study.disgroupportal.view.components.GrayColor
import com.study.disgroupportal.view.components.WhiteAbsolutelyColor

@Composable
fun EditTextField(
    value: MutableState<String>,
    onValueChange: (String) -> Unit = {},
    placeholder: String = "",
    modifier: Modifier = Modifier
) {
    val focus = remember {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        border = BorderStroke(
            width = 2.dp,
            color = when {
                focus.value -> CyanColor
                else -> Gray
            },
        ),
        colors = cardColors(WhiteAbsolutelyColor),
        shape = RoundedCornerShape(12.dp),
        elevation = cardElevation(1.dp)
    ) {
        BasicTextField(
            onValueChange = {
                value.value = it
                onValueChange(it)
            },
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
                .padding(18.dp, 8.dp)
                .onFocusChanged { focus.value = it.isFocused },
            keyboardOptions = KeyboardOptions(
                capitalization = Sentences,
                keyboardType = Text
            )
        ) { innerText ->
            if (value.value.isBlank()) {
                Text(
                    text = placeholder,
                    fontSize = 16.sp,
                    color = Gray
                )
            }
            innerText()
        }
    }
}