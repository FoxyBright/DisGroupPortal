package com.study.disgroupportal.model.navigation

import com.study.disgroupportal.model.navigation.DestinationArg.NEW_INFO_ARG
import com.study.disgroupportal.model.navigation.DestinationArg.REQUEST_INFO_ARG
import com.study.disgroupportal.model.navigation.DestinationArg.EMPLOYEE_INFO_ARG

enum class Destination(
    val route: String,
    val showBottomBar: Boolean = false
) {
    LOGIN("login"),

    NEWS("news", true),
    NEW_INFO("new_info/{${NEW_INFO_ARG.arg}}"),
    ADD_NEW("add_new/{${NEW_INFO_ARG.arg}}"),

    PORTAl("main", true),
    EMPLOYEES("employees", true),
    EMPLOYEE_INFO("employee_info/{${EMPLOYEE_INFO_ARG.arg}}"),
    ADD_EMPLOYEE("add_employee/{${EMPLOYEE_INFO_ARG.arg}}"),

    PROFILE("profile", true),

    REQUESTS("requests", true),
    REQUEST_INFO("request_info/{${REQUEST_INFO_ARG.arg}}"),
    ADD_REQUEST("add_request/{${REQUEST_INFO_ARG.arg}}"),
}