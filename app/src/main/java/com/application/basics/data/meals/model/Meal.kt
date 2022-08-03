package com.application.basics.data.meals.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "mealTable")
data class Meal(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name : String,
    val description : String
)
