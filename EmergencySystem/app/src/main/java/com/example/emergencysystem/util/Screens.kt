package com.example.emergencysystem.util

sealed class Screens(val route: String) {
    object SplashScreen: Screens("splash")
    object LoginScreen: Screens("login")
    object RegisterScreen: Screens("register")
    object HomeScreen: Screens("home")
    object ProfileScreen: Screens("profile")
    object EmergencyScreen: Screens("emergency")
    object MapScreen: Screens("doctor")
    object  TODO_LIST: Screens("todo_list")
    object ADD_EDIT_TODO: Screens("add_edit_todo")

}
