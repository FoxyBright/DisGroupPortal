package com.study.disgroupportal.view.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun DefaultPullRefreshContainer(
    refreshing: Boolean,
    modifier: Modifier = Modifier,
    pullRefreshState: PullRefreshState? = null,
    onRefresh: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    val refreshState: PullRefreshState = pullRefreshState
        ?: rememberPullRefreshState(
            refreshing = refreshing,
            onRefresh = onRefresh
        )

    Box(modifier.pullRefresh(refreshState)) {
        content()
        PullRefreshIndicator(
            modifier = Modifier.align(TopCenter),
            backgroundColor = WhiteColor,
            contentColor = CyanColor,
            refreshing = refreshing,
            state = refreshState
        )
    }
}