package com.hivian.lydia_test.core.services.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavDirections
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hivian.lydia_test.ui.composables.HomeScreen

internal class NavigationService: INavigationService {

    private lateinit var mainNavController: NavHostController

    override var navigationActivity: AppCompatActivity? = null

    override fun navigateBack(): Boolean = mainNavController.navigateUp() ?: false

    override fun openRandomUserDetail(userId: Int) {
        mainNavController.navigate(NavScreen.Detail.createRouteWithArgs(userId))
    }

    private fun navigateTo(directions: NavDirections) {
        mainNavController.navigate(directions)
    }

    @Composable
    override fun InitNavController() {
        mainNavController = rememberNavController()

        NavHost(
            navController = mainNavController,
            startDestination = NavScreen.Home.route,
        ) {
            IScreenRoute.allScreens.forEach { screen ->
                composable(
                    screen.route,
                    screen.arguments,
                    screen.deepLinks
                ) {
                    screen.Content(
                        navController = mainNavController,
                        navBackStackEntry = it
                    )
                }
            }
        }
    }

}
