package com.application.basics.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ReceiptLong
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.application.basics.R
import com.application.basics.data.meals.model.Meal
import com.application.basics.viewmodels.MealViewModel

@Composable
fun MealScreen(
    mealViewModel: MealViewModel = hiltViewModel(),
    navigateToNextScreen: () -> Unit
) {
    val meals by mealViewModel.meals.observeAsState(emptyList())
    val context = LocalContext.current
    if (meals.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn() {
            items(meals) { meal ->
                MealItem(meal) {
//                  Best to directly call a method of the viewModel
                    Toast.makeText(context, meal.name, Toast.LENGTH_SHORT).show()
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            // Navigates to the next Screen (In this case toggles the screens)
            FloatingActionButton(
                modifier = Modifier.padding(40.dp),
                onClick = navigateToNextScreen
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = stringResource(id = R.string.add)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun MealItem(meal: Meal = Meal(1, "Name", "Description"), onClick: () -> Unit = {}) {

    ElevatedCard(
        modifier = Modifier.padding(20.dp),
        onClick = {
            onClick()
        }
    ) {
        ListItem(
            icon = {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.Rounded.ReceiptLong,
                    contentDescription = null
                )
            },
            text = {
                Text(text = meal.name)
            },
            secondaryText = {
                Text(text = meal.description)
            }
        )
    }
}

