package com.application.basics.viewmodels

import androidx.lifecycle.*
import com.application.basics.data.meals.MealRepository
import com.application.basics.data.meals.model.Meal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    private val mealRepository: MealRepository
): ViewModel(){

    /**
     * A collection of meals reacting to the changes of the Database [[com.application.basics.data.RoomDatabase]].
     */
    val meals : LiveData<List<Meal>> = mealRepository.allMeals
        .map { meals -> meals.sortedBy { it.id } }
        .onStart {
            viewModelScope.launch {
                mealRepository.updateMeals()
            }
        }
        .asLiveData()

    // Additional behaviour and calculations

}