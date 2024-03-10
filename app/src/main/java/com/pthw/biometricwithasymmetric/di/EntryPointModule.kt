package com.pthw.biometricwithasymmetric.di

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import co.onenex.biometric.NexBiometric
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by P.T.H.W on 10/03/2024.
 */

private lateinit var entryPoint: BiometricSetupPageEntryPoint

@Composable
fun requireBiometricSetupPageEntryPoint(): BiometricSetupPageEntryPoint {
    if (!::entryPoint.isInitialized) {
        entryPoint = EntryPoints.get(
            LocalContext.current.applicationContext,
            BiometricSetupPageEntryPoint::class.java,
        )
    }
    return entryPoint
}


@EntryPoint
@InstallIn(SingletonComponent::class)
interface BiometricSetupPageEntryPoint {
    val nexBiometric: NexBiometric
}