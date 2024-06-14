package com.mobven.fitai.presentation.home.suggest

data class SuggestModel(
    var image: Int = 0,
    var name: String = "",
    var detail: String = "",
    var isSelected: Boolean = false
)