package com.nazwakhayla.sekaiprofileviewer.viewModel

import androidx.lifecycle.ViewModel
import com.nazwakhayla.sekaiprofileviewer.entity.Category
import com.nazwakhayla.sekaiprofileviewer.repository.CategoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CategoryViewModel : ViewModel() {
    private val categoryRepository : CategoryRepository = CategoryRepository()

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories = _categories.asStateFlow()

    init {
        loadCategories()
    }

    fun loadCategories(){
        categoryRepository.getAllCategories {
            _categories.value = it
        }
    }
}