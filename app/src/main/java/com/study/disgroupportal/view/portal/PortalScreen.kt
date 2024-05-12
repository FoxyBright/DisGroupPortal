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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
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
import com.study.disgroupportal.model.employee.Employee
import com.study.disgroupportal.model.navigation.Destination.PORTAl
import com.study.disgroupportal.model.portal.Departament
import com.study.disgroupportal.model.portal.Departament.Companion.getListByDivision
import com.study.disgroupportal.model.portal.Division
import com.study.disgroupportal.model.portal.Tile
import com.study.disgroupportal.tools.getViewModel
import com.study.disgroupportal.ui.theme.Background
import com.study.disgroupportal.viewmodel.EmployeeViewModel
import com.study.disgroupportal.viewmodel.MainViewModel

private const val defaultTitle = "Корпоративный портал"

@Composable
fun PortalScreen() {
    val focusManager = LocalFocusManager.current
    LaunchedEffect(Unit) { curScreen = PORTAl }

    val title = remember {
        mutableStateOf(defaultTitle)
    }

    val searchFocus = remember {
        mutableStateOf(false)
    }

    val selectedEmployee = remember {
        mutableStateOf<Employee?>(null)
    }
    val selectedDivision = remember {
        mutableStateOf<Division?>(null)
    }
    val selectedDepartament = remember {
        mutableStateOf<Departament?>(null)
    }

    val listState = rememberLazyListState()

    fun onBackClick() {
        selectedEmployee.value
            ?.let { selectedEmployee.value = null }
            ?: selectedDepartament.value
                ?.let { selectedDepartament.value = null }
            ?: selectedDivision.value
                ?.run { selectedDivision.value = null }
    }

    BackHandler(
        enabled = selectedEmployee.value != null
                || selectedDepartament.value != null
                || selectedDivision.value != null,
        onBack = ::onBackClick
    )

    LaunchedEffect(listState.isScrollInProgress) {
        if (listState.isScrollInProgress) {
            focusManager.clearFocus()
        }
    }

    Scaffold(
        topBar = {
            PortalTopBar(
                showSearch = selectedEmployee.value == null,
                showBackButton = title.value != defaultTitle,
                onBackClick = ::onBackClick,
                focus = searchFocus,
                title = title.value
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null
            ) { focusManager.clearFocus() }
    ) { paddings ->
        Content(
            modifier = Modifier.padding(paddings),
            searchFocus = searchFocus.value,
            employee = selectedEmployee,
            departament = selectedDepartament,
            division = selectedDivision,
            listState = listState,
            title = title
        )
    }
}

@Composable
private fun Content(
    searchFocus: Boolean,
    departament: MutableState<Departament?>,
    employee: MutableState<Employee?>,
    division: MutableState<Division?>,
    title: MutableState<String>,
    modifier: Modifier = Modifier,
    listState: LazyListState
) {
    val employeeVm = getViewModel<EmployeeViewModel>()
    val mainVm = getViewModel<MainViewModel>()
    LaunchedEffect(Unit) { employeeVm.uploadEmployees() }

    LaunchedEffect(division.value, departament.value, employee.value) {
        title.value = when {
            employee.value != null -> buildString {
                val user = employee.value!!
                val dep = user.departament
                append(dep?.division?.title ?: "")
                append("\n")
                append(dep?.title ?: "")
                append("\n")
                append(user.name)
            }

            departament.value != null -> run {
                if (division.value == null) {
                    return@run departament.value?.title ?: ""
                }
                val depTitle = departament.value?.title ?: ""
                val divTitle = division.value?.title ?: ""
                buildString {
                    append(divTitle)
                    append("\n")
                    append(depTitle)
                }
            }

            division.value != null -> division.value?.title ?: ""

            else -> defaultTitle
        }
    }

    val employees = remember(
        employeeVm.employees.toList(),
        employeeVm.searchText.value,
        division.value, departament.value
    ) {
        employeeVm.employees.filter { employee ->
            val inDivision = division.value
                ?.run { employee.departament?.division == this }
                ?: true
            val inDepartament = departament.value
                ?.run { employee.departament == this }
                ?: true
            employeeVm.searchText.value.let { text ->
                if (text.isNotBlank()) employee.run {
                    duties.any { it.contains(text) }
                            || name.contains(text)
                } else true
            }.let { it && inDepartament && inDivision }
        }
    }

    val tiles = remember(division.value, departament.value) {
        val list: List<Tile> = division.value
            ?.let { getListByDivision(it) }
            ?: Division.entries
        list.toMutableStateList()
    }

    val showEmployees by remember(
        employeeVm.searchText.value, departament.value, searchFocus
    ) {
        val show = employeeVm.searchText.value.isNotBlank()
                || departament.value != null
                || searchFocus
        mutableStateOf(show)
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = listState
    ) {
        when {
            employee.value != null -> item {
                EmployeeInfoScreen(employee.value!!)
            }

            showEmployees -> items(employees) {
                EmployeeItem(
                    employee = it,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) { employee.value = it }
                Spacer(Modifier.height(12.dp))
            }

            else -> itemsIndexed(tiles) { i, tile ->
                TileItem(tile, i % 2 != 0) { select ->
                    if (select is Departament) departament.value = select
                    if (select is Division) division.value = select
                }
            }
        }
    }
}