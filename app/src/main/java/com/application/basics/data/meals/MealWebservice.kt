package com.application.basics.data.meals

import android.util.Log
import com.application.basics.data.meals.model.Meal
import com.application.basics.data.meals.model.MealResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class MealWebservice @Inject constructor() {

    companion object {
        // Possible place to store the ENDPOINT
        @Suppress("unused")
        const val MEAL_ENDPOINT = "/essen"
    }

    private val json = Json { prettyPrint = true }

    @OptIn(ExperimentalSerializationApi::class)
    suspend fun getMeals(): MealResponse {

        // Json-String that is collected from the Api. See KTOR Client
        val jsonApiResponse = json.encodeToString(
            MealResponse(
                200,
                "test message",
                (1..10).asFlow()
                    .map { number -> Meal(number, "meal $number", "description of meal $number") }
                    .toList()
            )
        )

        Log.i("MealWebservice","Webservice Response : $jsonApiResponse")

//      Network delay
        delay(2000)

        // That's the line an actual Webservice could have
        return Json.decodeFromString(jsonApiResponse)

    }
/*
//    Actual Implementation of Webservice
//    standardClient() -> Ktor Client with features installed
//

    suspend fun getAlarms() : AlarmResponse = standardClient().get("$HOST$AlarmEndpoint") {
        headers{
            setStandardHeaders(tokenRepository)
        }
    }

*/

}