package com.pthw.biometricwithasymmetric.core.domain.usecase

import com.pocket.customer.domain.CoroutineUseCase
import com.pthw.biometricwithasymmetric.appbase.DispatcherProvider
import com.pocket.customer.domain.TwoParams
import com.pthw.biometricwithasymmetric.core.domain.repository.BiometricRepository
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