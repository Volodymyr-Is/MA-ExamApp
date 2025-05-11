package com.example.exam.presentation

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.presentation.navigation.SetupNavigation
import com.example.presentation.viewModels.JokeViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

@Composable
fun JokesApp(activity: ComponentActivity) {
    val navController = rememberNavController()
    val jokeViewModel: JokeViewModel = activity.getViewModel()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        CompositionLocalProvider(LocalNavController provides navController) {
            SetupNavigation(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                viewModel = jokeViewModel
            )
        }
    }
}

val LocalNavController = compositionLocalOf<NavController> {
    throw IllegalArgumentException("No NavController provided")
}
