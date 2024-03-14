package com.pthw.domain.usecase

import com.pthw.domain.DispatcherProvider
import com.pthw.domain.usecase.utils.CoroutineUseCase
import com.pthw.domain.usecase.utils.TwoParams
import com.pthw.domain.repository.BiometricRepository
import javax.inject.Inject

/**
 * Created by P.T.H.W on 05/03/2024.
 */
class CreateBiometricUseCase @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val biometricRepository: BiometricRepository
) : CoroutineUseCase<TwoParams<String, String>, String>(dispatcherProvider) {
    override suspend fun provide(params: TwoParams<String, String>): String {
        return biometricRepository.createBiometric(params.one, params.two)
    }
}