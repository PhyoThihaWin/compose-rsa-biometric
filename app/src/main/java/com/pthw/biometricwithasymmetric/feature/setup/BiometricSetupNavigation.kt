package com.pthw.biometricwithasymmetric.feature.setup

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

/**
 * Created by P.T.H.W on 08/03/2024.
 */

const val setupPageNavigationRoute = "setup/"

fun NavController.navigateToSetup(navOptions: NavOptions? = null) {
    this.navigate(setupPageNavigationRoute, navOptions)
}

fun NavGraphBuilder.biometricSetupPage(
    modifier: Modifier,
    onClick: () -> Unit
) {
    composable(
        route = setupPageNavigationRoute,
    ) {
        BiometricSetupPage(modifier, onClick)
    }
}
