package com.aithanasakis.adoptadog.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.aithanasakis.adoptadog.MainViewModel
import com.aithanasakis.adoptadog.ui.screens.DogDetailsScreen
import com.aithanasakis.adoptadog.ui.screens.DogsListScreen
import com.aithanasakis.adoptadog.ui.screens.dogsDetailsRouteName
import com.aithanasakis.adoptadog.ui.screens.dogsListRouteName

@Composable
fun NavigationGraph(mainViewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = dogsListRouteName) {
        composable(dogsListRouteName) {
            DogsListScreen(navController, mainViewModel)
        }

        composable(
            route = "${dogsDetailsRouteName}/{dogId}",
            arguments = listOf(navArgument("dogId") { type = NavType.IntType })
        ) { entry ->
            DogDetailsScreen(dogId = entry.arguments?.getInt("dogId") ?: -1, mainViewModel)
        }
    }
}