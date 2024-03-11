package com.pthw.biometricwithasymmetric.core.data.cache.di

import com.pthw.biometricwithasymmetric.core.data.cache.AuthStoreProviderImpl
import com.pthw.biometricwithasymmetric.core.domain.repository.AuthStoreProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by P.T.H.W on 11/03/2024.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {
    @Binds
    @Singleton
    abstract fun bindAuthStoreProvider(authStoreProviderImpl: AuthStoreProviderImpl): AuthStoreProvider
}