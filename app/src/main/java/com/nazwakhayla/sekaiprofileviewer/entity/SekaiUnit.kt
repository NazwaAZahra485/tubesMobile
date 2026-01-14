package com.nazwakhayla.sekaiprofileviewer.entity

data class SekaiUnit(
    val id: String = "",
    val description: String = "",
    val japanese_name: String = "",
    val logo_url: String = "",
    val main_vs: List<Long> = emptyList(),
    val members: List<Long> = emptyList(),
    val sekai: String = "",
    val unit_name: String = ""

) {

}