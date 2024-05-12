package com.study.disgroupportal.view.news

import android.net.Uri
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.study.disgroupportal.DisGroupPortalApp.Companion.curScreen
import com.study.disgroupportal.R
import com.study.disgroupportal.model.navigation.Destination.NEWS
import com.study.disgroupportal.model.navigation.Destination.NEW_INFO
import com.study.disgroupportal.model.navigation.DestinationArg.NEW_INFO_ARG
import com.study.disgroupportal.model.navigation.NavArgument
import com.study.disgroupportal.model.news.New
import com.study.disgroupportal.tools.Navigation.navigateTo
import com.study.disgroupportal.tools.getViewModel
import com.study.disgroupportal.view.components.DefaultPullRefreshContainer
import com.study.disgroupportal.view.components.GrayColor
import com.study.disgroupportal.view.components.ProgressIndicator
import com.study.disgroupportal.view.components.TeaColor
import com.study.disgroupportal.view.components.WhiteColor
import com.study.disgroupportal.viewmodel.NewsViewModel

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun NewsScreen(
    navHostController: NavHostController,
) {
    val newsVm = getViewModel<NewsViewModel>()

    LaunchedEffect(Unit) {
        curScreen = NEWS
        if (newsVm.news.isEmpty()) {
            newsVm.uploadNews()
        }
    }

    Crossfade(
        targetState = newsVm.pendingNews
                && newsVm.news.isEmpty(),
        label = "News animation",
    ) { loading ->
        DefaultPullRefreshContainer(
            refreshing = newsVm.refreshNews,
            onRefresh = {
                newsVm.refreshNews = true
                newsVm.uploadNews()
            }
        ) {
            Scaffold(
                topBar = {
                    Column {
                        Text(
                            text = stringResource(R.string.news),
                            fontWeight = Bold,
                            color = GrayColor,
                            fontSize = 30.sp,
                            modifier = Modifier.padding(
                                start = 24.dp, top = 30.dp
                            )
                        )
                        HorizontalDivider(
                            modifier = Modifier
                                .padding(top = 20.dp),
                            color = LightGray
                        )
                    }
                }
            ) { paddings ->
                if (loading) {
                    ProgressIndicator(Modifier.padding(paddings))
                } else {
                    LazyColumn(Modifier.padding(paddings)) {
                        item { Spacer(Modifier.height(20.dp)) }

                        itemsIndexed(newsVm.news) { i, new ->
                            NewItem(new) {
                                navHostController.navigateTo(
                                    arg = NavArgument(NEW_INFO_ARG, new.id),
                                    dest = NEW_INFO
                                )
                            }

                            if(i != newsVm.news.lastIndex){
                                HorizontalDivider(
                                    modifier = Modifier
                                        .padding(horizontal = 16.dp)
                                        .padding(vertical = 10.dp),
                                    color = LightGray
                                )
                            }
                        }

                        item { Spacer(Modifier.height(20.dp)) }
                    }
                }
            }
        }
    }
}

@Composable
private fun NewItem(
    new: New,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        verticalAlignment = CenterVertically
    ) {
        Text(
            fontWeight = SemiBold,
            text = new.title,
            fontSize = 18.sp,
            color = GrayColor,
            maxLines = 3,
            overflow = Ellipsis,
            modifier = Modifier
                .weight(1.4f)
                .padding(
                    start = 24.dp,
                    end = 12.dp
                )
        )

        when {
            new.imagePath.isNotBlank() -> Uri.parse(new.imagePath)
            new.imageUrl.isNotBlank() -> new.imageUrl
            else -> null
        }?.let { image ->
            AsyncImage(
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp)
                    .padding(end = 16.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentDescription = null,
                contentScale = Crop,
                model = image
            )
        } ?: run {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp)
                    .padding(end = 16.dp)
                    .background(
                        shape = RoundedCornerShape(12.dp),
                        color = TeaColor
                    ),
                contentAlignment = Center
            ) {
                Text(
                    text = stringResource(R.string.new_placeholder_image),
                    fontWeight = SemiBold,
                    color = WhiteColor,
                    fontSize = 30.sp
                )
            }
        }
    }
}