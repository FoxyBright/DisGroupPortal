package com.study.disgroupportal.view.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.study.disgroupportal.R
import com.study.disgroupportal.model.navigation.Destination.LOGIN
import com.study.disgroupportal.tools.Navigation.navigateTo
import com.study.disgroupportal.tools.getViewModel
import com.study.disgroupportal.view.components.GrayColor
import com.study.disgroupportal.view.components.WhiteColor
import com.study.disgroupportal.viewmodel.EmployeeViewModel
import com.study.disgroupportal.viewmodel.MainViewModel
import com.study.disgroupportal.viewmodel.NewsViewModel
import com.study.disgroupportal.viewmodel.RequestsViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ExitDialog(
    navHostController: NavHostController,
    showErrorDialog: MutableState<Boolean>,
) {
    val employeeVm = getViewModel<EmployeeViewModel>()
    val requestsVm = getViewModel<RequestsViewModel>()
    val newsVm = getViewModel<NewsViewModel>()
    val mainVm = getViewModel<MainViewModel>()

    if (showErrorDialog.value) {
        BasicAlertDialog(
            onDismissRequest = {
                showErrorDialog.value = false
            }
        ) {
            Column(
                horizontalAlignment = CenterHorizontally,
                modifier = Modifier
                    .background(
                        shape = RoundedCornerShape(16.dp),
                        color = WhiteColor
                    )
                    .padding(16.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.exit_dialog),
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
                    color = GrayColor,
                    fontSize = 16.sp
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
                            employeeVm.clearVm()
                            newsVm.clearNewVm()
                            requestsVm.clearVm()
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