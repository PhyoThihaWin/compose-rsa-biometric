package com.pthw.biometricwithasymmetric.core.data.network.di

import com.pthw.biometricwithasymmetric.core.data.network.biometric.BiometricService
import com.pthw.biometricwithasymmetric.core.data.network.biometric.mapper.SetupBiometricDataMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.ktor.client.HttpClient

/**
 * Created by P.T.H.W on 11/03/2024.
 */

@Module
@InstallIn(ViewModelComponent::class)
object MainServiceModule {

    @Provides
    fun provideMainService(
        ktor: HttpClient,
        setupBiometricDataMapper: SetupBiometricDataMapper
    ): BiometricService {
        return BiometricService(ktor, setupBiometricDataMapper)
    }

}