package com.example.recipeapp

sealed class Screens (val route: String){

    object Recipe : Screens(route = "recipeScreen")
    object Category_Details : Screens(route = "detailScreen/{category}")

}