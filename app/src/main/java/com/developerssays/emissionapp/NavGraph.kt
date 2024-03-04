package com.developerssays.emissionapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.developerssays.emissionapp.viewmodel.AuthViewModel
import com.developerssays.emissionapp.views.HomeScreen
import com.developerssays.emissionapp.views.LoginScreen
import com.developerssays.emissionapp.views.SignupScreen
import com.developerssays.emissionapp.views.UserInfo


sealed class Route(val route:String){
    object SplashScreen:Route("SplashScreen")
    object SignUpScreen:Route("SignUpScreen")
    object LoginScreen:Route("LoginScreen")
    object HomeScreen:Route("HomeScreen")
    object ProfileScreen:Route("ProfileScreen")
}



@Composable
fun AppNavHost(
    viewModel: AuthViewModel,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Route.LoginScreen.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Route.LoginScreen.route) {
           LoginScreen(viewModel, navController)
        }
        composable(Route.SignUpScreen.route) {
           SignupScreen(viewModel, navController)
        }
        composable(Route.HomeScreen.route) {
         HomeScreen(viewModel, navController)
        }
        composable(Route.ProfileScreen.route){
           UserInfo(viewModel,navController)
        }
    }
}
