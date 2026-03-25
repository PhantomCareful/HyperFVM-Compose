package com.careful.hyperfvm.compose.nevigation3

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.navigation3.runtime.NavKey

class NavigationViewModel : ViewModel() {
    val backStack = mutableStateListOf<NavKey>().apply {
        add(Route.MainPage)
    }
}