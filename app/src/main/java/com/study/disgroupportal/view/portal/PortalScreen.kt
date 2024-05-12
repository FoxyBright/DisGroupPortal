package com.study.disgroupportal.view.portal

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.study.disgroupportal.DisGroupPortalApp.Companion.curScreen
import com.study.disgroupportal.model.employee.UserRole.ADMIN
import com.study.disgroupportal.model.employee.UserRole.USER
import com.study.disgroupportal.model.navigation.Destination.PORTAl
import com.study.disgroupportal.model.portal.Departament
import com.study.disgroupportal.model.portal.Departament.Companion.getListByDivision
import com.study.disgroupportal.model.portal.Division
import com.study.disgroupportal.model.portal.Tile
import com.study.disgroupportal.tools.getViewModel
import com.study.disgroupportal.view.components.AddFloatingButton
import com.study.disgroupportal.view.components.WhiteColor
import com.study.disgroupportal.view.portal.employee.EmployeeInfoScreen
import com.study.disgroupportal.view.portal.employee.EmployeeItem
import com.study.disgroupportal.viewmodel.EmployeeViewModel
import com.study.disgroupportal.viewmodel.MainViewModel

private const val defaultTitle = "Корпоративный портал"

@Composable
fun PortalScreen() {
    val employeeVm = getViewModel<EmployeeViewModel>()
    val mainVm = getViewModel<MainViewModel>()
    val focusManager = LocalFocusManager.current
    LaunchedEffect(Unit) { curScreen = PORTAl }


    val title = remember {
        mutableStateOf(defaultTitle)
    }

    val searchFocus = remember {
        mutableStateOf(false)
    }

    val showEmployees by remember(
        employeeVm.selectedDepartament,
        employeeVm.searchText.value,
        searchFocus.value
    ) {
        val show = employeeVm.searchText.value.isNotBlank()
                || employeeVm.selectedDepartament != null
                || searchFocus.value
        mutableStateOf(show)
    }

    fun onBackClick() {
        employeeVm.selectedEmployee
            ?.let { employeeVm.selectedEmployee = null }
            ?: employeeVm.selectedDepartament
                ?.let { employeeVm.selectedDepartament = null }
            ?: employeeVm.selectedDivision
                ?.run { employeeVm.selectedDivision = null }
        employeeVm.isAddEmployee = false
    }

    BackHandler(
        enabled = employeeVm.selectedEmployee != null
                || employeeVm.selectedDepartament != null
                || employeeVm.selectedDivision != null
                || employeeVm.isAddEmployee,
        onBack = ::onBackClick
    )

    Scaffold(
        topBar = {
            PortalTopBar(
                showBackButton = title.value != defaultTitle,
                onBackClick = ::onBackClick,
                focus = searchFocus,
                title = title.value
            )
        },
        floatingActionButton = {
            if (mainVm.user?.role == ADMIN && showEmployees) {
                AddFloatingButton { employeeVm.isAddEmployee = true }
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .background(WhiteColor)
            .clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null
            ) { focusManager.clearFocus() }
    ) { paddings ->
        Content(
            modifier = Modifier.padding(paddings),
            showEmployees = showEmployees,
            onBackClick = ::onBackClick,
            title = title
        )
    }
}

@Composable
private fun Content(
    showEmployees: Boolean,
    title: MutableState<String>,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    val employeeVm = getViewModel<EmployeeViewModel>()
    LaunchedEffect(Unit) { employeeVm.uploadEmployees() }

    LaunchedEffect(
        employeeVm.selectedDepartament,
        employeeVm.selectedDivision,
        employeeVm.selectedEmployee
    ) {
        title.value = when {
            employeeVm.selectedEmployee != null -> buildString {
                val dep = employeeVm.selectedEmployee
                    ?.departament ?: return@buildString
                append(dep.division.title)
                append("\n")
                append(dep.title)
            }

            employeeVm.selectedDepartament != null -> buildString {
                val dep = employeeVm.selectedDepartament
                    ?: return@buildString
                append(dep.division.title)
                append("\n")
                append(dep.title)
            }

            employeeVm.selectedDivision != null -> {
                employeeVm.selectedDivision?.title ?: ""
            }

            else -> defaultTitle
        }
    }

    val employees = remember(
        employeeVm.selectedDepartament,
        employeeVm.employees.toList(),
        employeeVm.searchText.value,
        employeeVm.selectedDivision
    ) {
        employeeVm.employees.filter { employee ->
            val isUser = employee.role == USER
            val inDivision = employeeVm.selectedDivision
                ?.run { employee.departament?.division == this }
                ?: true
            val inDepartament = employeeVm.selectedDepartament
                ?.run { employee.departament == this }
                ?: true
            employeeVm.searchText.value.let { text ->
                if (text.isNotBlank()) employee.run {
                    duties.any { it.contains(text) }
                            || name.contains(text)
                } else true
            }.let { it && inDepartament && inDivision && isUser }
        }
    }

    val tiles = remember(employeeVm.selectedDivision) {
        val list: List<Tile> = employeeVm.selectedDivision
            ?.let { getListByDivision(it) }
            ?: Division.entries
        list.toMutableStateList()
    }

    LazyColumn(modifier.fillMaxSize()) {
        when {
            employeeVm.run {
                selectedEmployee != null || isAddEmployee
            } -> item {
                EmployeeInfoScreen(
                    employee = employeeVm.selectedEmployee,
                    isAdd = employeeVm.isAddEmployee,
                    onBack = onBackClick
                )
            }

            showEmployees -> items(employees) {
                EmployeeItem(
                    employee = it,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) { employeeVm.selectedEmployee = it }
                Spacer(Modifier.height(12.dp))
            }

            else -> itemsIndexed(tiles) { i, tile ->
                TileItem(tile, i % 2 != 0) { select ->
                    if (select is Departament) {
                        employeeVm.selectedDepartament = select
                    }
                    if (select is Division) {
                        employeeVm.selectedDivision = select
                    }
                }
            }
        }
    }
}