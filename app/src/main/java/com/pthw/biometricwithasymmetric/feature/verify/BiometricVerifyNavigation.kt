package com.pthw.biometricwithasymmetric.feature.verify

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

/**
 * Created by P.T.H.W on 08/03/2024.
 */

const val verifyPageNavigationRoute = "verify/"

fun NavController.navigateToVerifyPage(navOptions: NavOptions? = null) {
    this.navigate(verifyPageNavigationRoute, navOptions)
}

fun NavGraphBuilder.biometricVerifyPage() {
    composable(
        route = verifyPageNavigationRoute,
    ) {
        BiometricVerifyPage()
    }
}
