package com.jemy.thamanya.ui.ui.screens

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {
    private var _category = MutableStateFlow<CategoriesResponse.Category>(
       CategoriesResponse.Category()
    )
    var category: StateFlow<CategoriesResponse.Category> = _category


    fun setSubcategories(subList: CategoriesResponse.Category) {
        _category.value = subList
    }

}