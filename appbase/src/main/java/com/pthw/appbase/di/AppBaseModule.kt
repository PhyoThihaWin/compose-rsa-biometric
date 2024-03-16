package com.pthw.appbase.di

import com.pthw.appbase.exceptionmapper.ExceptionHandler
import com.pthw.appbase.exceptionmapper.ExceptionHandlerImpl
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
    abstract fun exceptionMapper(exceptionMapperImpl: ExceptionHandlerImpl): ExceptionHandler
}