package com.example.myplants.feature_main.presentation.util

sealed class Screen(val route: String) {
    object TasksList : Screen("tasks_list_screen")
    object PlantsList : Screen("plants_list_screen")
    object PlantDetail : Screen("plant_detail_screen")
    object EditPlant : Screen("plant_edit_screen")
    object NotificationsList : Screen("notifications_list_screen")
    object Profile : Screen("profile_screen")
}
