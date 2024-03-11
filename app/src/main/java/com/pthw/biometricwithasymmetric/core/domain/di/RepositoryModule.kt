package com.pthw.biometricwithasymmetric.core.domain.di

import com.pthw.biometricwithasymmetric.core.data.cache.AuthStoreProviderImpl
import com.pthw.biometricwithasymmetric.core.data.repository.BiometricRepositoryImpl
import com.pthw.biometricwithasymmetric.core.domain.repository.AuthStoreProvider
import com.pthw.biometricwithasymmetric.core.domain.repository.BiometricRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by P.T.H.W on 05/03/2024.
 */

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindAuthRepository(repositoryImpl: BiometricRepositoryImpl): BiometricRepository
}