package com.application.basics.viewmodels

import androidx.lifecycle.*
import com.application.basics.data.meals.MealRepository
import com.application.basics.data.meals.model.Meal
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class AddMealViewModel @AssistedInject constructor(
    private val mealRepository: MealRepository,
    @Assisted mealId: Int
) : ViewModel() {

    // Actually 2 values for EditTexts would be created.
    private val _meal: MutableLiveData<Meal> = MutableLiveData<Meal>().also {
            viewModelScope.launch {
                val meal = mealRepository.getMealById(mealId)
                it.postValue(meal)
            }
        }

    /**
     * Holds the currently selected Meal.
     */
    val meal = _meal as LiveData<Meal>

    /**
     * Changes the name of the meal in the text field, an extra variable for the state is better.
     */
    fun changeDescription(description: String) =
        _meal.postValue(meal.value!!.copy(description = description))

    /**
     * Changes the description of the meal in the text field, an extra variable for the state is better.
     */
    fun changeName(name: String) =
        _meal.postValue(meal.value!!.copy(name = name))

    /**
     * Adds or changes a Meal. If the name is empty it will try to delete it.
     * Navigates to list screen after.
     */
    fun addMeal(
        navigate: () -> Unit
    ) = viewModelScope.launch {
        meal.value?.let {
            if(it.name.isBlank()){
                try{
                    mealRepository.deleteMeal(it.id)
                }catch (e : Exception){
                    // Better to search the database for the meal and if present it can be deleted.
                }
            }else{
                mealRepository.addMeal(it)
            }
        }
        // Navigate back to the List
        navigate()
    }



    companion object {
        /**
         * Provide factory for assisted Inject
         */
        @Suppress("UNCHECKED_CAST")
        fun provideFactory(
            assistedFactory: AddMealViewModelAssistedFactory, // this is the Factory interface
            // declared above
            mealId: Int
        ): ViewModelProvider.NewInstanceFactory = object : ViewModelProvider.NewInstanceFactory() {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(mealId) as T
            }
        }
    }

    /**
     * Provide assistedFactory for Factory creation
     */
    @dagger.assisted.AssistedFactory
    interface AddMealViewModelAssistedFactory {
        fun create(mealId: Int): AddMealViewModel
    }
}