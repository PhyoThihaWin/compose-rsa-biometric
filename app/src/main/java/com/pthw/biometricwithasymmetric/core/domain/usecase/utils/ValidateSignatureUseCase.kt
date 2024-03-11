package com.pthw.biometricwithasymmetric.core.domain.usecase.utils

import com.pthw.biometricwithasymmetric.appbase.DispatcherProvider
import com.pthw.biometricwithasymmetric.core.domain.repository.BiometricRepository
import javax.inject.Inject

/**
 * Created by P.T.H.W on 11/03/2024.
 */
class ValidateSignatureUseCase @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val repository: BiometricRepository
) : CoroutineUseCase<TwoParams<String, String>, String>(dispatcherProvider) {
    override suspend fun provide(params: TwoParams<String, String>): String {
        return repository.validateSignature(params.one, params.two)
    }
}