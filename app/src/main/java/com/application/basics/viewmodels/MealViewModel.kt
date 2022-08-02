package com.application.basics.viewmodels

import androidx.lifecycle.*
import com.application.basics.data.meals.MealRepository
import com.application.basics.data.meals.model.Meal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    val mealRepository: MealRepository
): ViewModel(){

    val meals : LiveData<List<Meal>> = mealRepository.getMealsSorted().asLiveData()

    // Additional behaviour and calculations

    fun addMeal(meal : Meal) = viewModelScope.launch {
        // Add Meal to Repository
    }

}