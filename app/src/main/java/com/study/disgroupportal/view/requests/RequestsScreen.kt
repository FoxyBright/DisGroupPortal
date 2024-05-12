package com.study.disgroupportal.view.requests

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.study.disgroupportal.DisGroupPortalApp
import com.study.disgroupportal.R
import com.study.disgroupportal.model.navigation.Destination.ADD_REQUEST
import com.study.disgroupportal.model.navigation.Destination.REQUESTS
import com.study.disgroupportal.model.navigation.Destination.REQUEST_INFO
import com.study.disgroupportal.model.navigation.DestinationArg.REQUEST_INFO_ARG
import com.study.disgroupportal.model.navigation.NavArgument
import com.study.disgroupportal.model.requests.Request
import com.study.disgroupportal.model.requests.RequestStatus
import com.study.disgroupportal.model.requests.RequestStatus.CANCELED
import com.study.disgroupportal.model.requests.RequestStatus.CLOSED
import com.study.disgroupportal.model.requests.RequestStatus.OPENED
import com.study.disgroupportal.model.requests.RequestTheme
import com.study.disgroupportal.model.employee.UserRole.USER
import com.study.disgroupportal.tools.Navigation.navigateTo
import com.study.disgroupportal.tools.getViewModel
import com.study.disgroupportal.ui.theme.Background
import com.study.disgroupportal.ui.theme.PrimaryColor
import com.study.disgroupportal.view.components.DefaultPullRefreshContainer
import com.study.disgroupportal.view.components.ProgressIndicator
import com.study.disgroupportal.viewmodel.MainViewModel
import com.study.disgroupportal.viewmodel.RequestsViewModel

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun RequestsScreen(navHostController: NavHostController) {
    val mainVm = getViewModel<MainViewModel>()
    val requestsVm = getViewModel<RequestsViewModel>()

    val themeFilters = requestsVm
        .requestThemeFilters.collectAsState()

    val statusFilters = requestsVm
        .requestStatusFilters.collectAsState()

    val requestList = requestsVm.requests.collectAsState()

    val requests = remember(
        statusFilters.value, themeFilters.value, requestList.value
    ) {
        requestList.value.filter {
            val inStatus = statusFilters.value.run {
                if (isEmpty()) true else contains(it.status)
            }
            val inTheme = themeFilters.value.run {
                if (isEmpty()) true else contains(it.theme)
            }
            inStatus && inTheme
        }.toMutableStateList()
    }

    LaunchedEffect(Unit) {
        DisGroupPortalApp.curScreen = REQUESTS
        if (requestsVm.firstUploadRequests) {
            requestsVm.uploadRequests(mainVm.user)
        }
    }

    Crossfade(
        targetState = requestsVm.pendingRequests,
        label = "Requests animation"
    ) { loading ->
        Scaffold(
            topBar = {
                Filters(
                    themeFilters = themeFilters.value,
                    statusFilters = statusFilters.value
                )
            },
            floatingActionButton = {
                if (mainVm.user?.role == USER) {
                    Card(
                        elevation = cardElevation(4.dp),
                        colors = cardColors(White),
                        shape = CircleShape,
                        onClick = {
                            navHostController.navigateTo(
                                arg = NavArgument(
                                    argument = REQUEST_INFO_ARG,
                                    value = "-1"
                                ),
                                dest = ADD_REQUEST
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
                refreshing = requestsVm.refreshRequests,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Background)
                    .padding(padding),
                onRefresh = {
                    requestsVm.refreshRequests = true
                    requestsVm.uploadRequests(mainVm.user)
                }
            ) {
                when {
                    loading -> ProgressIndicator(Modifier.size(40.dp))
                    requests.isEmpty() -> RequestsPlaceholder()
                    else -> RequestsContent(
                        navHostController = navHostController,
                        requests = requests
                    )
                }
            }
        }
    }
}

@Composable
private fun Filters(
    themeFilters: List<RequestTheme>,
    statusFilters: List<RequestStatus>,
    modifier: Modifier = Modifier
) {
    val requestsVm = getViewModel<RequestsViewModel>()

    Column(Modifier.background(White)) {
        Text(
            modifier = Modifier.padding(top = 6.dp, start = 6.dp),
            text = stringResource(R.string.filters),
            fontWeight = SemiBold,
            color = PrimaryColor,
            fontSize = 20.sp
        )

        LazyRow(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = spacedBy(8.dp)
        ) {
            item { Spacer(Modifier.width(8.dp)) }

            item {
                FilterItem(
                    isSelected = statusFilters.isEmpty()
                            && themeFilters.isEmpty(),
                    text = stringResource(R.string.all)
                ) { requestsVm.clearFilters() }
            }

            items(RequestStatus.entries) { status ->
                val isSelected by remember(statusFilters) {
                    mutableStateOf(statusFilters.contains(status))
                }
                FilterItem(
                    isSelected = isSelected,
                    text = stringResource(
                        when (status) {
                            OPENED -> R.string.opened
                            CANCELED -> R.string.canceled
                            CLOSED -> R.string.closed
                        }
                    )
                ) { requestsVm.changeStatusFilter(status) }
            }

            items(RequestTheme.entries) { theme ->
                val isSelected by remember(themeFilters) {
                    mutableStateOf(themeFilters.contains(theme))
                }
                FilterItem(
                    text = stringResource(theme.label),
                    isSelected = isSelected
                ) { requestsVm.changeThemeFilter(theme) }
            }

            item { Spacer(Modifier.width(8.dp)) }
        }
    }
}

@Composable
private fun FilterItem(
    isSelected: Boolean,
    text: String,
    onClick: () -> Unit
) {
    Card(
        elevation = cardElevation(2.dp),
        shape = CircleShape,
        onClick = onClick,
        colors = cardColors(
            containerColor = if (isSelected) {
                PrimaryColor
            } else {
                White
            }
        )
    ) {
        Text(
            color = if (isSelected) {
                White
            } else {
                PrimaryColor
            },
            modifier = Modifier.padding(6.dp, 4.dp),
            fontWeight = Medium,
            fontSize = 14.sp,
            text = text
        )
    }
}

@Composable
private fun RequestsContent(
    requests: List<Request>,
    navHostController: NavHostController
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(horizontal = 16.dp)
    ) {
        item { Spacer(Modifier.height(12.dp)) }

        items(requests) { request ->
            RequestItem(request) {
                navHostController.navigateTo(
                    arg = NavArgument(
                        value = request.id.toString(),
                        argument = REQUEST_INFO_ARG
                    ),
                    dest = REQUEST_INFO
                )
            }

            Spacer(Modifier.height(12.dp))
        }
    }
}