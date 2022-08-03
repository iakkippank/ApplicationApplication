package com.application.basics.data.meals

import androidx.room.Dao
import androidx.room.Query
import com.application.basics.data.meals.model.Meal
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MealRepository @Inject constructor(
    private val mealDao : MealDao,
    private val mealWebservice: MealWebservice
) {

    // Actually the DAO from below
    val allMeals: Flow<List<Meal>> = mealDao.getAll()

    suspend fun updateMeals() = mealWebservice.getMeals().also {
        // Populate Meals from webservice
        mealDao.insertMeals(it.meals)
    }

    suspend fun addMeal(meal : Meal) = mealDao.insertMeals(listOf(meal))

    /**
     * @return the meal with [mealId] from the database
     */
    suspend fun getMealById(mealId: Int) : Meal = mealDao.getMealByID(mealId) ?: Meal(0,"","")

    suspend fun deleteMeal(mealId: Int) {
        mealDao.deleteMeal(mealId)
    }

}