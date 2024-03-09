package com.pthw.biometricwithasymmetric.appbase

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BaseAppModule {
    @Binds
    abstract fun dispatcherProvider(dispatcherProvider: DefaultDispatcherProvider): DispatcherProvider
}

