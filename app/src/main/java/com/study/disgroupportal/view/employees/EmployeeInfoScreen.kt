package com.study.disgroupportal.view.employees

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.study.disgroupportal.DisGroupPortalApp
import com.study.disgroupportal.R
import com.study.disgroupportal.data.DataSource.getEmployeeById
import com.study.disgroupportal.model.navigation.Destination.ADD_EMPLOYEE
import com.study.disgroupportal.model.navigation.Destination.EMPLOYEE_INFO
import com.study.disgroupportal.model.navigation.DestinationArg.EMPLOYEE_INFO_ARG
import com.study.disgroupportal.model.navigation.NavArgument
import com.study.disgroupportal.model.employee.Employee
import com.study.disgroupportal.model.employee.UserRole.ADMIN
import com.study.disgroupportal.tools.Navigation.navigateTo
import com.study.disgroupportal.tools.getViewModel
import com.study.disgroupportal.ui.theme.PrimaryColor
import com.study.disgroupportal.ui.theme.RedColor
import com.study.disgroupportal.view.components.ProgressIndicator
import com.study.disgroupportal.view.requests.TopBar
import com.study.disgroupportal.viewmodel.MainViewModel

@Composable
fun EmployeeInfoScreen(
    navHostController: NavHostController,
    employeeId: String
) {
    var employee by remember(employeeId) {
        mutableStateOf<Employee?>(null)
    }

    LaunchedEffect(Unit) {
        DisGroupPortalApp.curScreen = EMPLOYEE_INFO
        getEmployeeById(employeeId.toLong())
            .onFailure { navHostController.navigateUp() }
            .onSuccess { employee = it }
    }

    employee?.apply {
        Scaffold(
            topBar = { TopBar(navHostController) }
        ) { paddings ->
            Content(
                navHostController = navHostController,
                modifier = Modifier
                    .padding(paddings),
                employee = this
            )
        }
    } ?: run {
        ProgressIndicator()
    }
}

@Composable
private fun Content(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    employee: Employee,
) {
    val mainVm = getViewModel<MainViewModel>()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(Modifier.height(30.dp))

        Box(Modifier.fillMaxWidth()) {
            if (mainVm.user?.role == ADMIN) {
                Row(
                    verticalAlignment = CenterVertically,
                    modifier = Modifier.align(TopEnd),
                ) {
                    Icon(
                        imageVector = ImageVector
                            .vectorResource(R.drawable.ic_edit),
                        contentDescription = null,
                        tint = PrimaryColor,
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .size(24.dp)
                            .clickable(
                                interactionSource = remember {
                                    MutableInteractionSource()
                                },
                                indication = null
                            ) {
                                navHostController.navigateTo(
                                    arg = NavArgument(
                                        argument = EMPLOYEE_INFO_ARG,
                                        value = employee.id
                                    ),
                                    dest = ADD_EMPLOYEE
                                )
                            }
                    )

                    Icon(
                        imageVector = ImageVector
                            .vectorResource(R.drawable.ic_delete),
                        contentDescription = null,
                        tint = RedColor,
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .size(24.dp)
                            .clickable(
                                interactionSource = remember {
                                    MutableInteractionSource()
                                },
                                indication = null
                            ) {
                                mainVm.deleteEmployee(employee)
                                navHostController.navigateUp()
                            }
                    )
                }
            }
            EmployeeAvatar(
                modifier = Modifier.align(Center),
                employee = employee,
                size = 140.dp
            )
        }

        Spacer(Modifier.height(30.dp))

        Text(
            modifier = Modifier.align(CenterHorizontally),
            textAlign = TextAlign.Center,
            text = employee.name,
            fontWeight = Bold,
            color = DarkGray,
            fontSize = 22.sp
        )

        Spacer(Modifier.height(20.dp))

        Text(
            fontSize = 18.sp,
            color = Gray,
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = PrimaryColor,
                        fontWeight = SemiBold
                    )
                ) { append(stringResource(R.string.departament)) }
                append(" ")
                append(employee.departament?.title ?: "")
            }
        )

        Spacer(Modifier.height(10.dp))

        Text(
            fontSize = 18.sp,
            color = Gray,
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = PrimaryColor,
                        fontWeight = SemiBold
                    )
                ) { append(stringResource(R.string.post)) }
                append(" ")
                append(employee.post)
            }
        )

        Spacer(Modifier.height(20.dp))

        Text(
            modifier = Modifier.align(CenterHorizontally),
            text = stringResource(R.string.employee_description),
            textAlign = TextAlign.Center,
            fontWeight = SemiBold,
            color = PrimaryColor,
            fontSize = 20.sp
        )

        Spacer(Modifier.height(10.dp))

//        Text(
//            text = employee.description,
//            fontSize = 16.sp,
//            color = DarkGray
//        )
//
//        Spacer(Modifier.height(30.dp))

        Text(
            modifier = Modifier.align(CenterHorizontally),
            textAlign = TextAlign.Center,
            fontWeight = SemiBold,
            color = PrimaryColor,
            text = stringResource(R.string.merits),
            fontSize = 20.sp
        )

//        Spacer(Modifier.height(10.dp))
//
//        Text(
//            text = employee.merits,
//            fontSize = 16.sp,
//            color = DarkGray
//        )

        Spacer(Modifier.height(20.dp))
    }
}

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
                .background(PrimaryColor),
            contentAlignment = Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    R.drawable.profile_placeholder
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(size / 4 * 3)
                    .clip(CircleShape),
                tint = Color.White
            )
        }
    }
}