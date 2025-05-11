package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.presentation.screens.CategoriesScreen
import com.example.presentation.screens.FavoriteJokesScreen
import com.example.presentation.screens.JokeDetailScreen
import com.example.presentation.viewModels.JokeViewModel

@Composable
fun SetupNavigation(
    navController: NavHostController,
    modifier: Modifier,
    viewModel: JokeViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Directions.Categories().direction,
        modifier = modifier
    ) {
        composable(Directions.Categories().direction) {
            CategoriesScreen(
                onCategoryClick = { displayName, endpoint ->
                    navController.navigate(Directions.JokeDetail(endpoint, displayName).route)
                },
                onFavoritesClick = {
                    navController.navigate(Directions.Favorites().direction)
                }
            )
        }
        composable("joke/{endpoint}/{displayName}") { backStackEntry ->
            val endpoint = backStackEntry.arguments?.getString("endpoint") ?: ""
            val displayName = backStackEntry.arguments?.getString("displayName") ?: ""
            JokeDetailScreen(
                category = endpoint,
                displayName = displayName,
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(Directions.Favorites().direction) {
            FavoriteJokesScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}