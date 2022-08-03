package com.application.basics.data.meals

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.application.basics.data.meals.model.Meal
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {

    @Query("SELECT * FROM mealTable")
    fun getAll(): Flow<List<Meal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeals(meal: List<Meal> )

    @Query("SELECT * FROM mealTable WHERE id=:mealId")
    suspend fun getMealByID(mealId: Int): Meal?

    @Query("DELETE FROM mealTable WHERE id=:mealId")
    suspend fun deleteMeal(mealId: Int)
}