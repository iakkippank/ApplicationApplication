package com.application.basics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.application.basics.ui.AddMealScreen
import com.application.basics.ui.MealScreen
import com.application.basics.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {

                // Pass this Controller to every Compose Function that need to navigate the App or do it via Lambda
                val navigationController = rememberNavController()
                // The Main Composable could be in a separate file too

                // Surface just adjusts the background color depending on the Theme (e.g. darkmode).
                Surface{
                    // Destinations should be stored separately
                    NavHost(navController = navigationController, startDestination = "meal"){
                        composable("meal"){
                            MealScreen{ id ->
                                // Navigate to AddMealScreen
                                navigationController.navigate("addMeal/$id")
                            }
                        }
                        composable(
                            "addMeal/{mealId}",
                            arguments = listOf(navArgument("mealId"){
                                type = NavType.IntType
                                defaultValue = 0
                            })
                        ){ backStackEntry ->
                            AddMealScreen(
                                mealId = backStackEntry.arguments?.getInt("mealId") ?: 0
                            ){
                                // Navigate without a backstack entry
                                navigationController.navigate("meal"){
                                    popUpTo("meal")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
