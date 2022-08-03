package com.application.basics.ui

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.application.basics.R
import com.application.basics.ViewModelFactoryProvider
import com.application.basics.data.meals.model.Meal
import com.application.basics.viewmodels.AddMealViewModel
import dagger.hilt.android.EntryPointAccessors
import org.intellij.lang.annotations.JdkConstants

@Composable
fun getAddMealViewModel(mealId: Int): AddMealViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        ViewModelFactoryProvider::class.java
    ).addMealViewModelFactory()

    return viewModel(factory = AddMealViewModel.provideFactory(factory, mealId))
}


@Composable
fun AddMealScreen(
    mealId : Int,
    addMealViewModel: AddMealViewModel = getAddMealViewModel(mealId = mealId),
    navigate : () -> Unit
) {

    val meal by addMealViewModel.meal.observeAsState()

    // Edit Text to change Name and Description of the Meal
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = if(mealId == 0){
            stringResource(id = R.string.newmeal)
        }else{
            stringResource(id = R.string.meal) + " #${meal?.id ?: ""}"
        },
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.size(20.dp))
        OutlinedTextField(
            value = meal?.name ?: "",
            label = { Text(text = stringResource(id = R.string.name)) },
            onValueChange = {
                addMealViewModel.changeName(it)
            }
        )
        Spacer(modifier = Modifier.size(10.dp))

        OutlinedTextField(
            value = meal?.description ?: "",
            label = { Text(text = stringResource(id = R.string.description)) },
            onValueChange = {
                addMealViewModel.changeDescription(it)
            }
        )
    }

    // Button to save the Meal
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp)
    ) {
        FloatingActionButton(onClick = {
            addMealViewModel.addMeal(navigate)
        }) {
            Icon(
                imageVector = Icons.Rounded.Save,
                contentDescription = stringResource(id = R.string.add)
            )
        }
    }

}