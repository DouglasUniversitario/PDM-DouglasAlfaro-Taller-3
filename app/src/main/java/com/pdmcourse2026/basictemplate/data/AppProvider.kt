package com.pdmcourse2026.basictemplate.data

import android.content.Context
import com.pdmcourse2026.basictemplate.data.database.AppDatabase
import com.pdmcourse2026.basictemplate.data.remote.KtorClient
import com.pdmcourse2026.basictemplate.data.repository.OptionRepository
import com.pdmcourse2026.basictemplate.data.repository.OptionRepositoryImpl
import com.pdmcourse2026.basictemplate.data.repository.PlaceRepository
import com.pdmcourse2026.basictemplate.data.repository.PlaceRepositoryImpl

class AppProvider(context: Context) {

    // Base de datos local (singleton ya dentro)
    private val appDatabase = AppDatabase.getDatabase(context)
    private val optionDao = appDatabase.optionDao()

    // Repositories locales
    private val optionRepository: OptionRepository =
        OptionRepositoryImpl(optionDao)

    // Repositories del API (Ktor)
    private val placeRepository: PlaceRepository =
        PlaceRepositoryImpl(KtorClient.client)

    // Métodos para proveer cada repositorio
    fun provideOptionRepository(): OptionRepository {
        return optionRepository
    }

    fun providePlaceRepository(): PlaceRepository {
        return placeRepository
    }
}