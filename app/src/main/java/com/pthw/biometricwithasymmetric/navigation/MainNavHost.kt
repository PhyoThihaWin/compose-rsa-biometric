package com.pthw.biometricwithasymmetric.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.pthw.biometricwithasymmetric.feature.setup.biometricSetupPage
import com.pthw.biometricwithasymmetric.feature.verify.biometricVerifyPage
import com.pthw.biometricwithasymmetric.feature.verify.verifyPageNavigationRoute
import timber.log.Timber

/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = verifyPageNavigationRoute,
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {

        biometricSetupPage(
            modifier = modifier,
        ) {
            Timber.i("navigate: NavHost")

            navController.navigate(verifyPageNavigationRoute)
        }
        biometricVerifyPage()
    }
}
