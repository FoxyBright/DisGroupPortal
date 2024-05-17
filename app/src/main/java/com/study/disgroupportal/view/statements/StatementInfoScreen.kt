package com.study.disgroupportal.view.statements

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize.Max
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.input.KeyboardCapitalization.Companion.Sentences
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.text.style.TextAlign.Companion.End
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.study.disgroupportal.DisGroupPortalApp.Companion.curScreen
import com.study.disgroupportal.DisGroupPortalApp.Companion.curTime
import com.study.disgroupportal.R
import com.study.disgroupportal.data.DataSource.getStatementById
import com.study.disgroupportal.model.employee.Employee
import com.study.disgroupportal.model.employee.UserRole.ADMIN
import com.study.disgroupportal.model.navigation.Destination.STATEMENT_INFO
import com.study.disgroupportal.model.statement.Statement
import com.study.disgroupportal.model.statement.StatementStatus.CANCELED
import com.study.disgroupportal.model.statement.StatementStatus.CLOSED
import com.study.disgroupportal.model.statement.StatementStatus.OPENED
import com.study.disgroupportal.model.statement.StatementTheme
import com.study.disgroupportal.tools.convertToFormatDate
import com.study.disgroupportal.tools.getSettingsUri
import com.study.disgroupportal.tools.getViewModel
import com.study.disgroupportal.tools.imageTaker
import com.study.disgroupportal.tools.logE
import com.study.disgroupportal.tools.neededStoragePermissions
import com.study.disgroupportal.tools.openCamera
import com.study.disgroupportal.tools.openGallery
import com.study.disgroupportal.tools.shareWith
import com.study.disgroupportal.view.components.BlackColor
import com.study.disgroupportal.view.components.CyanColor
import com.study.disgroupportal.view.components.GrayColor
import com.study.disgroupportal.view.components.GreenColor
import com.study.disgroupportal.view.components.RedColor
import com.study.disgroupportal.view.components.TeaColor
import com.study.disgroupportal.view.components.WhiteColor
import com.study.disgroupportal.viewmodel.MainViewModel
import com.study.disgroupportal.viewmodel.StatementViewModel

@Composable
@OptIn(ExperimentalLayoutApi::class)
fun StatementInfoScreen(
    navHostController: NavHostController,
    statementId: String,
) {
    val statementVm = getViewModel<StatementViewModel>()
    val mainVm = getViewModel<MainViewModel>()
    val context = LocalContext.current

    val isAdd by remember(statementId) {
        mutableStateOf(statementId.toLong() == -1L)
    }

    var statement by remember {
        mutableStateOf(Statement())
    }

    LaunchedEffect(Unit) {
        "opened with id = $statementId".logE("loggy")

        curScreen = STATEMENT_INFO
        if (isAdd) return@LaunchedEffect
        "search statement $statementId".logE("loggy")

        getStatementById(statementId.toLong())
            .onFailure {
                "error $it".logE("loggy")
                navHostController.navigateUp()
            }
            .onSuccess {
                "find $it".logE("loggy")
                statement = it
            }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                onBackClick = navHostController::navigateUp,
                title = stringResource(
                    id = R.string.statement_of,
                    statement.theme.run {
                        "\n" + stringResource(label)
                    }
                )
            )
        }
    ) { paddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(24.dp))

            if (isAdd) {
                Text(
                    text = stringResource(R.string.statements_theme),
                    fontWeight = Medium,
                    color = TeaColor,
                    fontSize = 14.sp,
                    textAlign = End
                )

                FlowRow(
                    horizontalArrangement = spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    StatementTheme.entries.forEach {
                        StatementTheme(it, it == statement.theme) {
                            statement = statement.copy(theme = it)
                        }
                    }
                }

                Spacer(Modifier.height(8.dp))
            } else {
                Text(
                    text = stringResource(
                        R.string.status,
                        stringResource(statement.status.label)
                    ),
                    fontWeight = Medium,
                    color = GrayColor,
                    fontSize = 14.sp,
                )

                Spacer(Modifier.height(10.dp))

                val start by remember(statement) {
                    statement.dateStart
                        .convertToFormatDate("dd.MM.yy")
                        .let { mutableStateOf(it) }
                }

                Text(
                    text = stringResource(R.string.in_work, start),
                    fontWeight = Medium,
                    color = GrayColor,
                    fontSize = 14.sp,
                )

                if (statement.status != OPENED) {
                    Spacer(Modifier.height(10.dp))

                    val end by remember(statement) {
                        statement.dateEnd
                            .convertToFormatDate("dd.MM.yy")
                            .let { mutableStateOf(it) }
                    }

                    Text(
                        fontWeight = Medium,
                        color = GrayColor,
                        fontSize = 14.sp,
                        text = stringResource(R.string.is_closed, end),
                    )
                }

                Spacer(Modifier.height(10.dp))
            }

            statement.investmentPath?.let { image ->
                AsyncImage(
                    model = Uri.parse(image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .border(
                            shape = RoundedCornerShape(16.dp),
                            color = GrayColor,
                            width = 1.dp
                        )
                )

                Spacer(Modifier.height(16.dp))
            }

            ActionButtons(
                statement = statement,
                mainVm = mainVm,
                isAdd = isAdd,
                onPickPhoto = {
                    statement = statement.copy(
                        investmentPath = it
                    )
                },
                onSharedClick = {
                    shareWith(
                        link = "http://disgroupportal.com" +
                                "/statementId/${statement.id}",
                        context = context,
                        sbj = "Ссылка на " + context.getString(
                            R.string.statement_of,
                            statement.theme.run {
                                " " + context.getString(label)
                            }
                        )
                    )
                }
            )

            Spacer(Modifier.height(16.dp))

            if (isAdd) {
                MessageTextField(
                    value = statement.content,
                    placeholder = stringResource(R.string.add_content)
                ) {
                    statement = statement.copy(
                        authorName = mainVm.user?.name ?: "",
                        authorId = mainVm.user?.id ?: "-1",
                        content = it
                    )
                }
            }

            if (!isAdd && statement.content.isNotBlank()) {
                Text(
                    text = stringResource(R.string.content),
                    fontWeight = Medium,
                    color = TeaColor,
                    fontSize = 20.sp,
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = statement.content,
                    fontWeight = Medium,
                    color = GrayColor,
                    fontSize = 14.sp,
                )

                Spacer(Modifier.height(10.dp))
            }

            if (statement.authorName.isNotBlank()) {
                Text(
                    text = stringResource(
                        R.string.from,
                        statement.authorName
                    ),
                    modifier = Modifier.align(Alignment.End),
                    fontWeight = Medium,
                    color = TeaColor,
                    fontSize = 14.sp
                )
            }

            if (statement.status == OPENED
                && mainVm.user?.role == ADMIN
            ) {
                Spacer(Modifier.height(16.dp))

                MessageTextField(
                    value = statement.answer ?: "",
                    placeholder = stringResource(R.string.add_answer)
                ) {
                    statement = statement.copy(
                        answerAuthor = mainVm.user?.name ?: "",
                        answer = it
                    )
                }
            }

            if (!statement.answer.isNullOrBlank()
                && !statement.answerAuthor.isNullOrBlank()
                && statement.status != OPENED
            ) {
                Spacer(Modifier.height(30.dp))

                Text(
                    text = stringResource(
                        R.string.answer_from,
                        (statement.answerAuthor ?: "")
                    ),
                    fontWeight = Medium,
                    color = TeaColor,
                    fontSize = 20.sp,
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = statement.answer ?: "",
                    fontWeight = Medium,
                    color = GrayColor,
                    fontSize = 14.sp,
                )
            }

            Spacer(Modifier.height(30.dp))

            if (statement.status == OPENED
                && mainVm.user?.role == ADMIN
            ) {
                AdminButtons(
                    navHostController = navHostController,
                    statementVm = statementVm,
                    statement = statement,
                    user = mainVm.user,
                )
            }

            if (isAdd && mainVm.user?.role != ADMIN) {
                Spacer(Modifier.weight(1f))

                AnswerButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = R.string.save,
                    color = CyanColor
                ) {
                    statementVm.addStatement(
                        statement = statement.copy(
                            authorId = mainVm.user?.id ?: "-1",
                            authorName = mainVm.user?.name ?: ""
                        ),
                        user = mainVm.user
                    )
                    navHostController.navigateUp()
                }
            }

            if (!isAdd && mainVm.user?.role != ADMIN) {
                Spacer(Modifier.weight(1f))

                AnswerButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = R.string.delete_statement,
                    color = RedColor
                ) {
                    statementVm.deleteStatement(
                        statement = statement,
                        user = mainVm.user
                    )
                    navHostController.navigateUp()
                }
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun ActionButtons(
    mainVm: MainViewModel,
    statement: Statement,
    isAdd: Boolean,
    onPickPhoto: (String) -> Unit = {},
    onSharedClick: () -> Unit
) {
    val takedPhoto = remember {
        mutableStateOf<Uri?>(null)
    }

    val context = LocalContext.current
    val imageTaker = imageTaker(
        onSuccess = { onPickPhoto(toString()) },
        resultUri = takedPhoto.value
    )
    val imagePicker =
        rememberLauncherForActivityResult(GetContent()) { uri ->
            uri?.let { onPickPhoto(it.toString()) }
        }

    val permissionCameraState = rememberPermissionState(
        permission = Manifest.permission.CAMERA
    ) { isGranted ->
        if (isGranted) {
            imageTaker.openCamera(context, takedPhoto)
        }
    }

    val permissionStorageState =
        rememberMultiplePermissionsState(
            permissions = neededStoragePermissions
        ) { results ->
            if (results.all { it.value }) {
                imagePicker.launch("image/*")
            }
        }

    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth()
    ) {
        if ((mainVm.user?.role == ADMIN
                    && statement.status == OPENED)
            || isAdd
        ) {
            ActionButton(R.drawable.ic_camera) {
                when {
                    permissionCameraState.status.isGranted -> {
                        imageTaker.openCamera(context, takedPhoto)
                    }

                    !permissionCameraState.status.shouldShowRationale -> {
                        permissionCameraState.launchPermissionRequest()
                    }

                    else -> context.startActivity(
                        Intent(
                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        ).apply {
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            data = getSettingsUri(context.packageName)
                        }
                    )
                }
            }

            Spacer(Modifier.width(14.dp))

            ActionButton(R.drawable.ic_file) {
                permissionStorageState.openGallery(
                    imagePicker = imagePicker,
                    context = context
                )
            }

            Spacer(Modifier.width(14.dp))
        }

        if (!isAdd) {
            ActionButton(
                icon = R.drawable.ic_share,
                onClick = onSharedClick
            )
        }
    }
}

@Composable
private fun AdminButtons(
    statement: Statement,
    statementVm: StatementViewModel,
    user: Employee?,
    navHostController: NavHostController
) {
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        AnswerButton(
            text = R.string.cancel_statement,
            color = RedColor
        ) {
            statementVm.answerStatement(
                statement = statement.copy(
                    dateEnd = curTime,
                    status = CANCELED,
                ),
                user = user
            )
            navHostController.navigateUp()
        }

        Spacer(Modifier.width(12.dp))

        AnswerButton(
            text = R.string.approve_statement,
            color = GreenColor
        ) {
            statementVm.answerStatement(
                statement = statement.copy(
                    dateEnd = curTime,
                    status = CLOSED,
                ),
                user = user
            )
            navHostController.navigateUp()
        }
    }
}

@Composable
private fun MessageTextField(
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
) {
    BasicTextField(
        onValueChange = onValueChange,
        textStyle = TextStyle(
            fontWeight = Medium,
            color = TeaColor,
            fontSize = 14.sp
        ),
        value = value,
        maxLines = 1,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp, top = 6.dp)
            .border(1.dp, GrayColor, RoundedCornerShape(16.dp))
            .padding(10.dp, 6.dp),
        keyboardOptions = KeyboardOptions(
            capitalization = Sentences,
            keyboardType = Text
        )
    ) { innerText ->
        Column {
            Text(
                text = placeholder,
                fontSize = 14.sp,
                color = Gray
            )
            Spacer(Modifier.height(2.dp))
            innerText()
        }
    }
}

@Composable
private fun AnswerButton(
    text: Int,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        elevation = cardElevation(2.dp),
        modifier = modifier.width(Max),
        colors = cardColors(color),
        shape = CircleShape,
        onClick = onClick
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp, 8.dp),
            text = stringResource(text),
            textAlign = Center,
            fontWeight = Medium,
            color = WhiteColor,
            fontSize = 12.sp,
        )
    }
}

@Composable
private fun ActionButton(
    icon: Int,
    onClick: () -> Unit
) {
    Card(
        border = BorderStroke(1.dp, GrayColor),
        colors = cardColors(Transparent),
        shape = RoundedCornerShape(8.dp),
        elevation = cardElevation(0.dp),
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(icon),
            modifier = Modifier
                .padding(8.dp)
                .size(32.dp),
            contentDescription = null,
            tint = GrayColor
        )
    }
}

@Composable
private fun StatementTheme(
    theme: StatementTheme,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        elevation = cardElevation(1.dp),
        colors = cardColors(
            if (isSelected) {
                CyanColor
            } else {
                BlackColor
            }
        ),
        shape = CircleShape,
        onClick = onClick
    ) {
        Text(
            modifier = Modifier.padding(10.dp, 8.dp),
            text = stringResource(theme.label),
            fontWeight = Medium,
            color = WhiteColor,
            fontSize = 10.sp,
            textAlign = End
        )
    }
}

@Composable
private fun TopBar(
    title: String,
    onBackClick: () -> Unit
) {
    Row(
        horizontalArrangement = SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 20.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_back_arrow),
            contentDescription = null,
            tint = BlackColor,
            modifier = Modifier
                .size(24.dp)
                .clickable(onClick = onBackClick)
        )

        Spacer(Modifier.width(8.dp))

        Text(
            modifier = Modifier
                .weight(1f)
                .padding(top = 4.dp),
            fontWeight = Medium,
            fontSize = 20.sp,
            color = TeaColor,
            textAlign = End,
            text = title
        )
    }
}