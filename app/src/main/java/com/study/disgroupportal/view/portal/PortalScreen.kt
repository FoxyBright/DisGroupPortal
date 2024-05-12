package com.study.disgroupportal.view.portal

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.study.disgroupportal.DisGroupPortalApp.Companion.curScreen
import com.study.disgroupportal.model.portal.Departament
import com.study.disgroupportal.model.portal.Departament.Companion.getListByDivision
import com.study.disgroupportal.model.portal.Division
import com.study.disgroupportal.model.portal.Tile
import com.study.disgroupportal.model.navigation.Destination.PORTAl
import com.study.disgroupportal.model.employee.Employee
import com.study.disgroupportal.tools.getViewModel
import com.study.disgroupportal.ui.theme.Background
import com.study.disgroupportal.viewmodel.MainViewModel

@Composable
fun PortalScreen() {
    val focusManager = LocalFocusManager.current
    LaunchedEffect(Unit) { curScreen = PORTAl }

    val searchFocus = remember {
        mutableStateOf(false)
    }

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(Background)
        .clickable(
            interactionSource = remember {
                MutableInteractionSource()
            },
            indication = null
        ) { focusManager.clearFocus() },
        topBar = {
            PortalTopBar(
                title = "Корпоративный портал",
                focus = searchFocus
            )
        }
    ) { paddings ->
        Content(
            modifier = Modifier.padding(paddings),
            searchFocus = searchFocus.value
        )
    }
}

@Composable
private fun Content(
    searchFocus: Boolean,
    modifier: Modifier = Modifier
) {
    val mainVm = getViewModel<MainViewModel>()
    LaunchedEffect(Unit) { mainVm.uploadEmployees() }

    var division by remember {
        mutableStateOf<Division?>(null)
    }
    var departament by remember {
        mutableStateOf<Departament?>(null)
    }
    var employee by remember {
        mutableStateOf<Employee?>(null)
    }

    BackHandler(
        enabled = departament != null
                || division != null
                || employee != null
    ) {
        employee?.let { employee = null }
            ?: departament?.let { departament = null }
            ?: division?.run { division = null }
    }

    val employees = remember(
        mainVm.employees.toList(),
        mainVm.searchText.value,
        division, departament
    ) {
        mainVm.employees.filter { employee ->
            val inDivision = division
                ?.run { employee.departament?.division == this }
                ?: true
            val inDepartament = departament
                ?.run { employee.departament == this }
                ?: true
            mainVm.searchText.value.run {
                if (isNotBlank()) {
                    employee.name.contains(this)
                } else true
            }.let { it && inDepartament && inDivision }
        }
    }

    val tiles = remember(division, departament) {
        val list: List<Tile> = division
            ?.let { getListByDivision(it) }
            ?: Division.entries
        list.toMutableStateList()
    }

    Crossfade(
        targetState = mainVm.searchText.value.isNotBlank()
                || departament != null
                || searchFocus,
        label = "animation"
    ) { showEmployees ->
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            when {
                employee != null -> item {
                    EmployeeInfoScreen(employee!!)
                }

                showEmployees -> items(employees) {
                    EmployeeItem(employee = it) { employee = it }
                }

                else -> itemsIndexed(tiles) { i, tile ->
                    TileItem(tile, i % 2 != 0) { select ->
                        if (select is Departament) departament = select
                        if (select is Division) division = select
                    }
                }
            }
        }
    }
}