package com.pdmcourse2026.basictemplate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdmcourse2026.basictemplate.data.remote.KtorClient
import com.pdmcourse2026.basictemplate.data.repository.PlaceRepositoryImpl
import com.pdmcourse2026.basictemplate.screens.home.HomeScreen
import com.pdmcourse2026.basictemplate.screens.home.ResultadosScreen
import com.pdmcourse2026.basictemplate.viewmodel.HomeViewModel
import com.pdmcourse2026.basictemplate.viewmodel.ResultsViewModel

@Composable
fun RankeUCA_App() {
    val backStack = rememberNavBackStack(Routes.Home)

    // Repositorio compartido instanciado con el cliente Ktor
    val repository = remember { PlaceRepositoryImpl(KtorClient.client) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {

            entry<Routes.Home> {
                val viewModel: HomeViewModel = viewModel(
                    factory = viewModelFactory {
                        initializer { HomeViewModel(repository) }
                    }
                )
                HomeScreen(
                    viewModel = viewModel,
                    onNavigateToResults = { backStack.add(Routes.Resultados) }
                )
            }

            entry<Routes.Resultados> {
                val viewModel: ResultsViewModel = viewModel(
                    factory = viewModelFactory {
                        initializer { ResultsViewModel(repository) }
                    }
                )
                ResultadosScreen(
                    viewModel = viewModel,
                    onNavigateToHome = {
                        // Limpiamos el stack y volvemos a Home
                        while (backStack.size > 1) {
                            backStack.removeLastOrNull()
                        }
                    }
                )
            }
        }
    )
}
