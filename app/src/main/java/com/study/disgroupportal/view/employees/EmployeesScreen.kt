package com.study.disgroupportal.view.employees

import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize.Max
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.study.disgroupportal.DisGroupPortalApp
import com.study.disgroupportal.R
import com.study.disgroupportal.model.navigation.Destination.ADD_EMPLOYEE
import com.study.disgroupportal.model.navigation.Destination.EMPLOYEES
import com.study.disgroupportal.model.navigation.Destination.EMPLOYEE_INFO
import com.study.disgroupportal.model.navigation.DestinationArg.EMPLOYEE_INFO_ARG
import com.study.disgroupportal.model.navigation.NavArgument
import com.study.disgroupportal.model.employee.Employee
import com.study.disgroupportal.model.employee.UserRole.ADMIN
import com.study.disgroupportal.tools.Navigation.navigateTo
import com.study.disgroupportal.tools.getViewModel
import com.study.disgroupportal.ui.theme.Background
import com.study.disgroupportal.ui.theme.EcologyColor
import com.study.disgroupportal.ui.theme.PrimaryColor
import com.study.disgroupportal.view.components.DefaultButton
import com.study.disgroupportal.view.components.DefaultPullRefreshContainer
import com.study.disgroupportal.view.components.ProgressIndicator
import com.study.disgroupportal.viewmodel.MainViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EmployeesScreen(navHostController: NavHostController) {

    val mainVm = getViewModel<MainViewModel>()

    LaunchedEffect(Unit) {
        DisGroupPortalApp.curScreen = EMPLOYEES
        if (mainVm.employees.isEmpty()) {
            mainVm.uploadEmployees()
        }
    }

    Crossfade(
        targetState = mainVm.pendingEmployees,
        label = "Requests animation"
    ) { loading ->
        Scaffold(
            floatingActionButton = {
                if (mainVm.user?.role == ADMIN) {
                    Card(
                        elevation = cardElevation(4.dp),
                        colors = cardColors(White),
                        shape = CircleShape,
                        onClick = {
                            navHostController.navigateTo(
                                arg = NavArgument(
                                    argument = EMPLOYEE_INFO_ARG,
                                    value = "-1"
                                ),
                                dest = ADD_EMPLOYEE
                            )
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_plus),
                            contentDescription = null,
                            tint = PrimaryColor,
                            modifier = Modifier
                                .padding(16.dp)
                                .size(20.dp)
                        )
                    }
                }
            }
        ) { padding ->
            DefaultPullRefreshContainer(
                refreshing = mainVm.refreshEmployees,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Background)
                    .padding(padding),
                onRefresh = {
                    mainVm.refreshEmployees = true
                    mainVm.uploadEmployees()
                }
            ) {
                if (loading) {
                    ProgressIndicator( Modifier.size(40.dp))
                } else {
                    EmployeesContent(
                        navHostController = navHostController,
                        employees = mainVm.employees
                    )
                }
            }
        }
    }
}

@Composable
private fun EmployeesContent(
    employees: List<Employee>,
    navHostController: NavHostController
) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(horizontal = 16.dp)
    ) {
        item { Spacer(Modifier.height(12.dp)) }

        item {
            DefaultButton(
                text = stringResource(R.string.call_confirence),
                textColor = White,
                color = EcologyColor
            ) {
                Toast.makeText(
                    context,
                    context.getString(R.string.add_later),
                    LENGTH_LONG
                ).show()
            }
        }

        item { Spacer(Modifier.height(20.dp)) }

        item {
            Text(
                text = stringResource(R.string.employees_list),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = PrimaryColor,
                fontWeight = SemiBold,
                fontSize = 24.sp
            )
        }

        item { Spacer(Modifier.height(20.dp)) }

        items(employees) { employee ->
            EmployeeItem(employee) {
                navHostController.navigateTo(
                    arg = NavArgument(
                        value = employee.id.toString(),
                        argument = EMPLOYEE_INFO_ARG
                    ),
                    dest = EMPLOYEE_INFO
                )
            }

            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
private fun EmployeeItem(
    employee: Employee,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.height(Max),
        elevation = cardElevation(2.dp),
        colors = cardColors(White),
        onClick = onClick
    ) {
        Row(
            verticalAlignment = CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            EmployeeAvatar(employee, 80.dp)

            Spacer(Modifier.width(8.dp))

            Column {
                Text(
                    text = employee.name,
                    fontWeight = Medium,
                    fontSize = 18.sp,
                    color = DarkGray
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = employee.post,
                    fontWeight = Normal,
                    fontSize = 14.sp,
                    color = Gray
                )
            }
        }
    }
}