package com.study.disgroupportal.view.portal

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize.Max
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults.iconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.input.KeyboardCapitalization.Companion.Sentences
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.study.disgroupportal.R
import com.study.disgroupportal.tools.getViewModel
import com.study.disgroupportal.view.components.BlackColor
import com.study.disgroupportal.view.components.CyanColor
import com.study.disgroupportal.view.components.GrayColor
import com.study.disgroupportal.view.components.TeaColor
import com.study.disgroupportal.view.components.WhiteAbsolutelyColor
import com.study.disgroupportal.view.components.WhiteColor
import com.study.disgroupportal.viewmodel.EmployeeViewModel

@Composable
fun PortalTopBar(
    focus: MutableState<Boolean>,
    title: String,
    showBackButton: Boolean = false,
    onBackClick: () -> Unit = {}
) {
    val employeeVm = getViewModel<EmployeeViewModel>()

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(BlackColor, TeaColor)
                    )
                )
                .padding(16.dp, 20.dp),
            contentAlignment = Center
        ) {
            if (showBackButton) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .align(TopStart)
                        .size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_back_arrow),
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = null,
                        tint = WhiteColor
                    )
                }
            }

            Text(
                textAlign = TextAlign.Center,
                fontWeight = SemiBold,
                color = WhiteColor,
                fontSize = 20.sp,
                text = title
            )
        }

        if (employeeVm.selectedEmployee == null
            && !employeeVm.isAddEmployee
        ) {
            Spacer(Modifier.height(12.dp))

            SearchRow(
                modifier = Modifier.padding(horizontal = 16.dp),
                value = employeeVm.searchText,
                focus = focus
            )

            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
private fun SearchRow(
    value: MutableState<String>,
    focus: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    val employeeVm = getViewModel<EmployeeViewModel>()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(Max),
        horizontalArrangement = SpaceBetween,
        verticalAlignment = CenterVertically
    ) {
        Card(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            border = BorderStroke(
                width = 2.dp,
                color = when {
                    focus.value -> CyanColor
                    else -> Gray
                },
            ),
            colors = cardColors(WhiteAbsolutelyColor),
            elevation = cardElevation(1.dp),
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

        if (focus.value) {
            val focusManager = LocalFocusManager.current
            Spacer(Modifier.width(8.dp))

            IconButton(
                colors = iconButtonColors(CyanColor),
                onClick = {
                    employeeVm.searchText.value = ""
                    focusManager.clearFocus()
                },
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
            ) {
                Icon(
                    imageVector = Default.Clear,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(3.dp),
                    tint = WhiteColor
                )
            }
        }
    }
}