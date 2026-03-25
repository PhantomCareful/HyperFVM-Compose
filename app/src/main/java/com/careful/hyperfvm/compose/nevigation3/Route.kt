package com.careful.hyperfvm.compose.nevigation3

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * Type-safe navigation keys for Navigation3.
 * Each destination is a NavKey (data object/data class) and can be saved/restored in the back stack.
 */
sealed interface Route : NavKey {
    @Serializable
    data object MainPage : Route

    @Serializable
    data object CoContributorPage : Route

    @Serializable
    data object ThanksGamePage : Route

    @Serializable
    data object ThanksAppPage : Route
}