package com.pthw.appbase.di

import com.pthw.data.repository.BiometricRepositoryImpl
import com.pthw.domain.repository.BiometricRepository
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