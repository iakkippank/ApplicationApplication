package com.application.basics.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.application.basics.data.meals.MealDao
import com.application.basics.data.meals.model.Meal

@Database(
    entities = [Meal::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDatabase : RoomDatabase() {

    abstract fun mealDao(): MealDao

}