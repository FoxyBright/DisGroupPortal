package com.study.disgroupportal.view.login

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement.End
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize.Max
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush.Companion.horizontalGradient
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation.Companion.None
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.study.disgroupportal.DisGroupPortalApp.Companion.curScreen
import com.study.disgroupportal.R
import com.study.disgroupportal.model.navigation.Destination.LOGIN
import com.study.disgroupportal.model.navigation.Destination.PORTAl
import com.study.disgroupportal.tools.Navigation.navigateTo
import com.study.disgroupportal.tools.getViewModel
import com.study.disgroupportal.view.components.BlackColor
import com.study.disgroupportal.view.components.CyanColor
import com.study.disgroupportal.view.components.GrayColor
import com.study.disgroupportal.view.components.TeaColor
import com.study.disgroupportal.view.components.WhiteColor
import com.study.disgroupportal.viewmodel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navHostController: NavHostController) {
    val focusManager = LocalFocusManager.current
    val mainVm = getViewModel<MainViewModel>()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) { curScreen = LOGIN }

    var isAdministrateMode by remember {
        mutableStateOf(false)
    }

    val login = remember {
        mutableStateOf("")
    }

    val password = remember {
        mutableStateOf("")
    }

    var loginError by remember {
        mutableStateOf(false)
    }

    var passwordError by remember {
        mutableStateOf(false)
    }

    val showErrorDialog = remember {
        mutableStateOf(false)
    }

    var errorDialogMessage by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = horizontalGradient(
                    colors = listOf(BlackColor, TeaColor)
                )
            )
            .padding(horizontal = 16.dp)
            .clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                }, indication = null
            ) { focusManager.clearFocus() },
    ) {
        Spacer(Modifier.height(30.dp))

        Row(
            verticalAlignment = CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                modifier = Modifier.size(120.dp),
                contentDescription = null
            )

            Text(
                text = stringResource(R.string.corp_portal),
                modifier = Modifier.padding(start = 10.dp),
                color = WhiteColor,
                fontSize = 22.sp
            )
        }

        Spacer(Modifier.weight(1f))

        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalAlignment = CenterHorizontally
        ) {
            Spacer(Modifier.height(24.dp))

            if (!isAdministrateMode) {
                LoginTextField(
                    placeholder = stringResource(R.string.login),
                    isError = loginError,
                    value = login
                )

                Spacer(Modifier.height(12.dp))
            }

            LoginTextField(
                placeholder = stringResource(R.string.password),
                isError = passwordError,
                value = password,
                hideValue = true
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = stringResource(
                    if (isAdministrateMode) {
                        R.string.return_to_common_mode
                    } else {
                        R.string.admin_mode
                    }
                ),
                modifier = Modifier
                    .align(CenterHorizontally)
                    .clickable {
                        isAdministrateMode = !isAdministrateMode
                    },
                color = CyanColor,
                fontSize = 16.sp
            )

            Spacer(Modifier.height(32.dp))

            Crossfade(
                targetState = mainVm.pendingLogin,
                modifier = Modifier.height(Max),
                label = "Login animation"
            ) { loading ->
                if (loading) {
                    Box(Modifier.fillMaxSize(), Center) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(60.dp),
                            strokeWidth = 4.dp,
                            color = WhiteColor,
                            strokeCap = Round
                        )
                    }
                } else {
                    TextButton(
                        onClick = {
                            if (login.value.isBlank() && !isAdministrateMode) {
                                scope.launch {
                                    loginError = true
                                    delay(2000L)
                                    loginError = false
                                }
                                if (password.value.isNotBlank()) {
                                    return@TextButton
                                }
                            }
                            if (password.value.isBlank()) {
                                scope.launch {
                                    passwordError = true
                                    delay(2000L)
                                    passwordError = false
                                }
                                return@TextButton
                            }
                            mainVm.login(
                                isAdministrateMode = isAdministrateMode,
                                password = password.value,
                                login = login.value,
                                onSuccess = {
                                    navHostController.navigateTo(
                                        dropScreens = true,
                                        dest = PORTAl
                                    )
                                },
                                onFailure = { error ->
                                    error.localizedMessage?.let {
                                        errorDialogMessage = it
                                    }
                                    showErrorDialog.value = true
                                }
                            )
                        },
                        colors = buttonColors(CyanColor),
                        shape = CircleShape
                    ) {
                        Text(
                            text = stringResource(R.string.enter_gosuslugi),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            fontWeight = Medium,
                            color = WhiteColor,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }

        Spacer(Modifier.weight(1f))

        Text(
            text = stringResource(R.string.copirite),
            modifier = Modifier.align(CenterHorizontally),
            color = LightGray,
            fontSize = 12.sp
        )

        Spacer(Modifier.height(20.dp))
    }

    ErrorDialog(
        errorDialogMessage = errorDialogMessage,
        showErrorDialog = showErrorDialog
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun ErrorDialog(
    showErrorDialog: MutableState<Boolean>,
    errorDialogMessage: String
) {
    if (showErrorDialog.value) {
        BasicAlertDialog(
            onDismissRequest = {
                showErrorDialog.value = false
            }
        ) {
            Column(
                horizontalAlignment = CenterHorizontally,
                modifier = Modifier
                    .background(WhiteColor, RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.error_title),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = SemiBold,
                    color = GrayColor,
                    fontSize = 24.sp
                )

                Spacer(Modifier.height(10.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = errorDialogMessage,
                    fontWeight = Normal,
                    color = GrayColor,
                    fontSize = 16.sp
                )

                Spacer(Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = End
                ) {
                    TextButton(
                        onClick = { showErrorDialog.value = false },
                        colors = buttonColors(Transparent),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.ok),
                            fontWeight = SemiBold,
                            color = TeaColor,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun LoginTextField(
    placeholder: String,
    value: MutableState<String>,
    modifier: Modifier = Modifier,
    hideValue: Boolean = false,
    isError: Boolean = false,
) {
    var focused by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = modifier
            .height(Max)
            .border(
                shape = CircleShape,
                color = when {
                    isError -> Red
                    focused -> CyanColor
                    else -> WhiteColor
                },
                width = 1.dp
            )
    ) {
        BasicTextField(
            onValueChange = {
                val text = if (it.firstOrNull() == ' ') {
                    it.substringAfter(" ")
                } else it
                value.value = text
            },
            value = value.value,
            textStyle = TextStyle(
                fontWeight = Medium,
                color = WhiteColor,
                fontSize = 18.sp
            ),
            singleLine = true,
            maxLines = 1,
            cursorBrush = SolidColor(CyanColor),
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp, 8.dp)
                .onFocusChanged {
                    focused = it.isFocused
                },
            keyboardOptions = KeyboardOptions(
                keyboardType = Text
            ),
            visualTransformation = if (hideValue) {
                PasswordVisualTransformation()
            } else {
                None
            }
        ) { innerText ->
            if (value.value.isBlank()) {
                Text(
                    color = Gray,
                    fontSize = 16.sp,
                    text = placeholder
                )
            }
            innerText()
        }
    }
}