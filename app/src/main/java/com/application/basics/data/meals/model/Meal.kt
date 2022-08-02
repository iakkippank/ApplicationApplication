package com.application.basics.data.meals.model

import kotlinx.serialization.Serializable

@Serializable
data class Meal(
    val id : Int,
    val name : String,
    val description : String
)
