package com.application.basics.data.meals

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

class InvoiceWebservice @Inject() constructor() {

    companion object {
        const val MEAL_ENDPOINT = "host/essen"
    }

    @OptIn(ExperimentalSerializationApi::class)
    suspend fun getMeals(): MealResponse {

        // That's the string that is collected from the Api actually KTOR Client would do that
        val jsonApiResponse = Json.encodeToString(
            MealResponse(
                200,
                "test message",
                (1..5).asFlow()
                    .map { number -> Meal(number, "meal $number", "description of meal $number") }
                    .toList()
            )
        )

//      Network delay
        delay(500)

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