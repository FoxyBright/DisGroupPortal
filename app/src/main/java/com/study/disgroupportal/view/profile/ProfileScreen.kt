package com.study.disgroupportal.view.profile

import androidx.annotation.DrawableRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.study.disgroupportal.BuildConfig.VERSION_NAME
import com.study.disgroupportal.DisGroupPortalApp.Companion.curScreen
import com.study.disgroupportal.R
import com.study.disgroupportal.model.navigation.Destination.LOGIN
import com.study.disgroupportal.model.navigation.Destination.PROFILE
import com.study.disgroupportal.model.employee.Employee
import com.study.disgroupportal.tools.Navigation.navigateTo
import com.study.disgroupportal.tools.getViewModel
import com.study.disgroupportal.ui.theme.Background
import com.study.disgroupportal.ui.theme.GrayColor
import com.study.disgroupportal.ui.theme.RedColor
import com.study.disgroupportal.ui.theme.TeaColor
import com.study.disgroupportal.view.components.DefaultButton
import com.study.disgroupportal.viewmodel.MainViewModel

@Composable
fun ProfileScreen(navHostController: NavHostController) {
    val mainVm = getViewModel<MainViewModel>()

    LaunchedEffect(Unit) { curScreen = PROFILE }

    val showDbSettings = remember {
        mutableStateOf(false)
    }

    val showExitDialog = remember {
        mutableStateOf(false)
    }

    Column(Modifier.background(Background)) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(TeaColor, Black)
                    ),
                    shape = RoundedCornerShape(
                        bottomEnd = 12.dp,
                        bottomStart = 12.dp
                    )
                )
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(6f)
                .background(Background)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = CenterHorizontally
    ) {
        Spacer(Modifier.height(100.dp))

        HeaderCard(
            onLogoutClick = { showExitDialog.value = true },
            onSettingsClick = {},
            user = mainVm.user
        )

        Spacer(Modifier.height(20.dp))

        DutyCard(mainVm.user)

        Spacer(Modifier.height(20.dp))

        Contacts(mainVm.user)

        DbSettingsCard(showDbSettings)

        Spacer(Modifier.weight(1f))

        Text(
            text = stringResource(R.string.version, VERSION_NAME),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
                .pointerInput(Unit) {
                    detectTapGestures(onLongPress = {
                        showDbSettings.apply { value = !value }
                    })
                },
            textAlign = TextAlign.Center,
            fontWeight = Medium,
            fontSize = 14.sp,
            color = Gray
        )

        Spacer(Modifier.height(70.dp))
    }

    ExitDialog(
        navHostController = navHostController,
        showErrorDialog = showExitDialog
    )
}

@Composable
private fun DutyCard(user: Employee?) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = cardElevation(2.dp),
        colors = cardColors(White),
        shape = RoundedCornerShape(16.dp)
    ) {
        Spacer(Modifier.height(16.dp))

        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            text = "Должностные обязанности:",
            fontWeight = SemiBold,
            fontSize = 18.sp,
            color = GrayColor
        )

        Spacer(Modifier.height(10.dp))

//        val userDuties = remember(user) {
//            (user?.duty?.list() ?: emptyList()).toMutableStateList()
//        }
//
//        if (userDuties.isNotEmpty()) userDuties.forEachIndexed { i, duty ->
//            DutyRow(duty, i != userDuties.lastIndex)
//        } else repeat(5) {
//            DutyRow(" ", it != 4)
//        }

        Spacer(Modifier.height(10.dp))
    }
}

@Composable
private fun DutyRow(
    text: String,
    addDivider: Boolean = true
) {
    Text(
        modifier = Modifier.padding(16.dp, 6.dp),
        fontSize = 16.sp,
        color = GrayColor,
        text = text
    )

    if (addDivider) {
        HorizontalDivider(color = LightGray)
    }
}

@Composable
private fun Contacts(user: Employee?) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = cardElevation(2.dp),
        colors = cardColors(White),
        shape = RoundedCornerShape(16.dp)
    ) {
        Spacer(Modifier.height(16.dp))

        ContactRow(
            icon = R.drawable.ic_email,
            contact = user?.email
                ?: stringResource(R.string.none)
        )

        HorizontalDivider(
            modifier = Modifier.padding(16.dp, 6.dp),
            color = LightGray
        )

        ContactRow(
            icon = R.drawable.ic_phone,
            contact = user?.phone
                ?: stringResource(R.string.none)
        )

        Spacer(Modifier.height(10.dp))
    }
}

@Composable
private fun ContactRow(
    @DrawableRes
    icon: Int,
    contact: String,
) {
    Row(
        verticalAlignment = CenterVertically,
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
            text = contact
        )
    }
}

@Composable
private fun HeaderCard(
    user: Employee?,
    onSettingsClick: () -> Unit,
    onLogoutClick: () -> Unit,
) {
    Box {
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = cardElevation(2.dp),
            colors = cardColors(White),
            shape = RoundedCornerShape(16.dp)
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
                    imageVector = ImageVector
                        .vectorResource(R.drawable.ic_settings),
                    contentDescription = null,
                    tint = GrayColor,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onSettingsClick() }
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
                fontSize = 24.sp,
                color = GrayColor
            )

            Text(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(horizontal = 16.dp),
                text = user?.email ?: " ",
                fontSize = 14.sp,
                color = Gray
            )

            Spacer(Modifier.height(16.dp))
        }

        AsyncImage(
            modifier = Modifier
                .offset(y = (-60).dp)
                .size(120.dp)
                .background(White, CircleShape)
                .clip(CircleShape)
                .align(TopCenter)
                .border(3.dp, White, CircleShape),
            model = user?.avatarUrl,
            contentDescription = null,
            contentScale = Crop
        )
    }
}

@Composable
private fun DbSettingsCard(
    showDbSettings: MutableState<Boolean>
) {
    val mainVm = getViewModel<MainViewModel>()

    Crossfade(
        targetState = showDbSettings.value,
        label = "Database control buttons animation"
    ) { showButtons ->
        if (showButtons) {
            Column {
                Spacer(Modifier.height(20.dp))

                DefaultButton(
                    text = stringResource(R.string.set_database_presets),
                    textColor = White,
                    color = Black
                ) {
                    mainVm.setDatabasePresets()
                    showDbSettings.value = false
                }

                Spacer(Modifier.height(10.dp))

                DefaultButton(
                    text = stringResource(R.string.clear_database),
                    textColor = White,
                    color = RedColor
                ) {
                    mainVm.clearDatabase()
                    showDbSettings.value = false
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun ExitDialog(
    navHostController: NavHostController,
    showErrorDialog: MutableState<Boolean>,
) {
    val mainVm = getViewModel<MainViewModel>()

    if (showErrorDialog.value) {
        BasicAlertDialog(
            onDismissRequest = {
                showErrorDialog.value = false
            }
        ) {
            Column(
                modifier = Modifier
                    .background(White, RoundedCornerShape(16.dp))
                    .padding(16.dp),
                horizontalAlignment = CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.exit_dialog),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = SemiBold,
                    color = GrayColor,
                    fontSize = 24.sp
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.exit_agree),
                    fontWeight = Normal,
                    fontSize = 16.sp,
                    color = GrayColor
                )

                Spacer(Modifier.height(30.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = { showErrorDialog.value = false },
                        colors = buttonColors(Transparent),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.cancel),
                            fontWeight = SemiBold,
                            color = GrayColor,
                            fontSize = 18.sp
                        )
                    }

                    Spacer(Modifier.width(16.dp))

                    TextButton(
                        onClick = {
                            showErrorDialog.value = false
                            navHostController.navigateTo(
                                dropScreens = true,
                                dest = LOGIN
                            )
                            mainVm.logout()
                        },
                        colors = buttonColors(Transparent),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            fontWeight = SemiBold,
                            text = stringResource(R.string.ok),
                            color = GrayColor,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}