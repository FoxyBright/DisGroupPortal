package com.study.disgroupportal.view.statements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.study.disgroupportal.DisGroupPortalApp.Companion.curScreen
import com.study.disgroupportal.R
import com.study.disgroupportal.model.employee.UserRole.ADMIN
import com.study.disgroupportal.model.navigation.Destination.STATEMENTS
import com.study.disgroupportal.model.navigation.Destination.STATEMENT_INFO
import com.study.disgroupportal.model.navigation.DestinationArg.STATEMENT_INFO_ARG
import com.study.disgroupportal.model.navigation.NavArgument
import com.study.disgroupportal.model.statement.Statement
import com.study.disgroupportal.model.statement.StatementStatus.CLOSED
import com.study.disgroupportal.model.statement.StatementStatus.OPENED
import com.study.disgroupportal.tools.Navigation.navigateTo
import com.study.disgroupportal.tools.convertToFormatDate
import com.study.disgroupportal.tools.getViewModel
import com.study.disgroupportal.view.components.AddFloatingButton
import com.study.disgroupportal.view.components.BlackColor
import com.study.disgroupportal.view.components.TeaColor
import com.study.disgroupportal.view.components.WhiteAbsolutelyColor
import com.study.disgroupportal.view.components.WhiteColor
import com.study.disgroupportal.viewmodel.MainViewModel
import com.study.disgroupportal.viewmodel.StatementViewModel

@Composable
fun StatementsScreen(navHostController: NavHostController) {
    val statementVm = getViewModel<StatementViewModel>()
    val mainVm = getViewModel<MainViewModel>()

    LaunchedEffect(Unit) {
        curScreen = STATEMENTS
        statementVm.uploadStatements(mainVm.user)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            if (mainVm.user?.role != ADMIN) {
                AddFloatingButton {
                    navHostController.navigateTo(
                        arg = NavArgument(
                            argument = STATEMENT_INFO_ARG,
                            value = -1L
                        ),
                        dest = STATEMENT_INFO
                    )
                }
            }
        },
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(BlackColor, TeaColor)
                        )
                    )
                    .padding(16.dp, 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.reporting),
                    textAlign = TextAlign.Center,
                    fontWeight = SemiBold,
                    color = WhiteColor,
                    fontSize = 20.sp
                )
            }
        }
    ) { paddings ->
        LazyColumn(
            verticalArrangement = spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(WhiteColor)
                .padding(paddings)
                .padding(horizontal = 12.dp)
        ) {
            statementVm.statements
                .filter { it.status == OPENED }
                .let { list ->
                    if (list.isNotEmpty()) item {
                        Spacer(Modifier.height(16.dp))

                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            text = stringResource(R.string.opened_statements),
                            fontWeight = SemiBold,
                            fontSize = 18.sp,
                            color = Black
                        )
                    }

                    items(list) {
                        StatementItem(it) {
                            navHostController.navigateTo(
                                arg = NavArgument(
                                    argument = STATEMENT_INFO_ARG,
                                    value = it.id
                                ),
                                dest = STATEMENT_INFO
                            )
                        }
                    }
                }

            statementVm.statements
                .filter { it.status != OPENED }
                .let { list ->
                    if (list.isNotEmpty()) item {
                        Spacer(Modifier.height(16.dp))
                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            text = stringResource(R.string.closed_statements),
                            fontWeight = SemiBold,
                            fontSize = 18.sp,
                            color = Black
                        )
                    }

                    items(list) {
                        StatementItem(it) {
                            navHostController.navigateTo(
                                arg = NavArgument(
                                    argument = STATEMENT_INFO_ARG,
                                    value = it.id
                                ),
                                dest = STATEMENT_INFO
                            )
                        }
                    }
                }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun StatementItem(
    statement: Statement,
    onClick: () -> Unit
) {
    Card(
        colors = cardColors(WhiteAbsolutelyColor),
        shape = RoundedCornerShape(16.dp),
        elevation = cardElevation(2.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = statement.theme.label.let {
                    stringResource(
                        R.string.statement_of,
                        stringResource(it)
                    )
                },
                fontWeight = Medium,
                fontSize = 16.sp,
                color = Black
            )

            Spacer(Modifier.height(40.dp))

            Row(
                verticalAlignment = CenterVertically,
                horizontalArrangement = SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (statement.status != OPENED) {
                    Text(
                        text = stringResource(
                            id = statement.status.label
                        ).uppercase(),
                        color = statement.status.color,
                        fontWeight = Medium,
                        fontSize = 14.sp
                    )
                }

                Text(
                    text = buildString {
                        val dateStart = remember(statement) {
                            statement.dateStart
                                .convertToFormatDate("dd.MM.yy")
                                .let { mutableStateOf(it) }
                        }
                        append(dateStart.value)
                        if (
                            statement.status == CLOSED
                            && statement.dateEnd != 0L
                        ) {
                            val dateEnd = remember(statement) {
                                statement.dateEnd
                                    .convertToFormatDate("dd.MM.yy")
                                    .let { mutableStateOf(it) }
                            }
                            append(" - ")
                            append(dateEnd.value)
                        }
                    },
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End,
                    fontWeight = Medium,
                    fontSize = 10.sp,
                    color = Black
                )
            }
        }
    }
}