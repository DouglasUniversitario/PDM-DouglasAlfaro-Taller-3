package com.pdmcourse2026.basictemplate.data

import android.content.Context
import com.pdmcourse2026.basictemplate.data.database.AppDatabase
import com.pdmcourse2026.basictemplate.data.remote.KtorClient
import com.pdmcourse2026.basictemplate.data.repository.OptionRepository
import com.pdmcourse2026.basictemplate.data.repository.OptionRepositoryImpl
import com.pdmcourse2026.basictemplate.data.repository.PlaceRepository
import com.pdmcourse2026.basictemplate.data.repository.PlaceRepositoryImpl
import com.pdmcourse2026.basictemplate.data.repository.QuestionRepository
import com.pdmcourse2026.basictemplate.data.repository.QuestionRepositoryImpl

class AppProvider(context: Context) {

    // Base de datos local (singleton ya dentro)
    private val appDatabase = AppDatabase.getDatabase(context)
    private val questionDao = appDatabase.questionDao()
    private val optionDao = appDatabase.optionDao()

    // Repositories locales
    private val questionRepository: QuestionRepository =
        QuestionRepositoryImpl(questionDao)
    private val optionRepository: OptionRepository =
        OptionRepositoryImpl(optionDao)

    // Repositories del API (Ktor)
    private val placeRepository: PlaceRepository =
        PlaceRepositoryImpl(KtorClient.client)

    // Métodos para proveer cada repositorio
    fun provideQuestionRepository(): QuestionRepository {
        return questionRepository
    }
    fun provideOptionRepository(): OptionRepository {
        return optionRepository
    }

    fun providePlaceRepository(): PlaceRepository {
        return placeRepository
    }
}