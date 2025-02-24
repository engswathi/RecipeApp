package com.example.recipeapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson

@Composable
fun setNavGraph(navController: NavHostController){

    NavHost(navController, startDestination = "recipeScreen") {
        composable(
            "detailScreen/{category}",
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryJson = backStackEntry.arguments?.getString("category")
            val category = categoryJson?.let {
                Gson().fromJson(it, Category::class.java)
            }

            if (category != null) {
                CategoryDetailScreen(navController, category)
            }
        }

        composable(route = "recipeScreen"){
            RecipeScreen(navController = navController)
        }
    }}