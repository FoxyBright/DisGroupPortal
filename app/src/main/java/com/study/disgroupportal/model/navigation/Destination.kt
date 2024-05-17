package com.study.disgroupportal.model.navigation

import com.study.disgroupportal.model.navigation.DestinationArg.NEW_INFO_ARG
import com.study.disgroupportal.model.navigation.DestinationArg.STATEMENT_INFO_ARG

enum class Destination(val route: String) {
    LOGIN("login"),

    NEWS("news"),
    NEW_INFO("new_info/{${NEW_INFO_ARG.arg}}"),
    ADD_NEW("add_new/{${NEW_INFO_ARG.arg}}"),

    PORTAl("main"),

    PROFILE("profile"),

    STATEMENTS("statements"),
    STATEMENT_INFO("statement_info/{${STATEMENT_INFO_ARG.arg}}")
}