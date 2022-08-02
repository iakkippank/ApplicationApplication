package com.application.basics.data.meals.model

import kotlinx.serialization.Serializable

@Serializable
data class MealResponse (
    val status : Int,
    val message : String,
    val meals : List<Meal>
)

