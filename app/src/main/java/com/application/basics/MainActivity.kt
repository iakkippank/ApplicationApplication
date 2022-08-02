package com.application.basics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                // Destinations should be stored separately
                NavHost(navController = navigationController, startDestination = "meal"){
                    composable("meal"){
                        MealScreen(){
                            navigationController.navigate("addMeal")
                        }
                    }
                    composable("addMeal"){
                        MealScreen(){
                            navigationController.navigate("meal")
                        }

                    }
                }
            }
        }
    }
}
