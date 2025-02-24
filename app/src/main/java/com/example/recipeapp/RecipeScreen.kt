package com.example.recipeapp
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.google.gson.Gson


@Composable
fun RecipeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
){
    val viewModel: CategoryViewModel = viewModel()
    val categoryState by viewModel.categoriesList

    Box(modifier = Modifier.fillMaxSize()){

        when {

            categoryState.isLoading ->

            {
                CircularProgressIndicator()
            }
            categoryState.isError !=null -> {

                Text("Error Loading Data")
            }
            else ->
            {
                CategoryScreen(categoryState.categoriesList,navController)
            }
        }
    }

}


@Composable
fun CategoryScreen(categories: List<Category>, navController: NavHostController){
    LazyVerticalGrid(
        GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize() ) {
        items(categories){
            item -> CategoryItem(item,navController)
        }
    }
}

@Composable
fun CategoryItem(category: Category, navController: NavHostController){

    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxSize()
        .clickable(true, onClick ={
            val categoryJson = Uri.encode(Gson().toJson(category))
            navController.navigate("detailScreen/$categoryJson")

        } ),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )

        Text(
            text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top=4.dp)
        )
    }


}