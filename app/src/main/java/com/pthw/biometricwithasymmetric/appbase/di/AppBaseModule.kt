package com.pthw.biometricwithasymmetric.appbase.di

import com.pthw.biometricwithasymmetric.appbase.exceptionmapper.ExceptionMapper
import com.pthw.biometricwithasymmetric.appbase.exceptionmapper.ExceptionMapperImpl
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
abstract class BaseAppModule {
    @Binds
    @Singleton
    abstract fun exceptionMapper(exceptionMapperImpl: ExceptionMapperImpl): ExceptionMapper
}