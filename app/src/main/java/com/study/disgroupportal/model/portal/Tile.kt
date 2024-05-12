package com.study.disgroupportal.model.portal

sealed interface Tile {
    val id: String
    val image: Int
    val title: String
    val description: String
}