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
//    mealDao : MealDao
    val mealWebservice: MealWebservice
) {

    // Actually the DAO from below
    private val allMeals: Flow<List<Meal>> = flow {
        emit(mealWebservice.getMeals().meals)
    }

    fun getMealsSorted(): Flow<List<Meal>> = allMeals.map { list ->
        list.sortedBy { it.id }
    }


    suspend fun updateMeals() = mealWebservice.getMeals().also {
        // Write to Room Database
        // mealDao.inject(this.meals)
    }
}

@Dao
interface MealDao {

    //    @Query("SELECT * FROM mealTable")
    suspend fun getAll(): Flow<List<Meal>>

    // Additional Database functions
}