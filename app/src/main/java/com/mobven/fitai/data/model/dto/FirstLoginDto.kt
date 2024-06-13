package com.mobven.fitai.data.model.dto

data class FirstLoginDto(
    val gender: String? = "Erkek",
    val height: Int? = 180,
    val firstWeight: Int? = 80,
    val targetWeight: Int? = 70,
    val dateOfBirth: String? = "2002-04-01",
    val goals: String? = "Kilo Kaybı"
) {

    constructor() : this(
        "Erkek",
        180,
        80,
        70,
        "2002-04-01",
        "Kilo Kaybı"
    )
}