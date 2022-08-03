package com.application.basics

import android.content.Context
import androidx.room.Room
import com.application.basics.data.RoomDatabase
import com.application.basics.data.meals.MealDao
import com.application.basics.data.meals.MealWebservice
import com.application.basics.viewmodels.AddMealViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMealWebservice() = MealWebservice()

    @Singleton
    @Provides
    fun provideAlarmDao(appDatabase: RoomDatabase) : MealDao {
        return appDatabase.mealDao()
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): RoomDatabase {
        return Room
            .databaseBuilder(
                appContext.applicationContext,
                RoomDatabase::class.java,
                "app_database")
            .fallbackToDestructiveMigration()
            .build()
    }
}


@EntryPoint
@InstallIn(ActivityComponent::class)
interface ViewModelFactoryProvider {
    fun addMealViewModelFactory(): AddMealViewModel.AddMealViewModelAssistedFactory
}

