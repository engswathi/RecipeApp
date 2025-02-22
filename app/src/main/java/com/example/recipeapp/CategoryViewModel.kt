package com.example.recipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {

    private val _categoryList = mutableStateOf(RecipeState())
    val categoriesList: State<RecipeState> = _categoryList

    init {
        fetchCategoriesList()
    }

    private fun fetchCategoriesList() {

        viewModelScope.launch {
            try {
                val response = retrofitService.getCategories()
                _categoryList.value = _categoryList.value.copy(
                    isLoading = false,
                    categoriesList = response.categories,
                    isError = null
                )
            } catch (e: Exception) {

                _categoryList.value = _categoryList.value.copy(
                    isLoading = false,
                    categoriesList = emptyList(),
                    isError = "Error fetching categories ${e.message}"

                )

            }
        }
    }
}

data class RecipeState(
    val isLoading: Boolean = true,
    val categoriesList: List<Category> = emptyList(),
    val isError: String? = null
)