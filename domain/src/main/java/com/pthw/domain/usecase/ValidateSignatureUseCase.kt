package com.pthw.domain.usecase

import com.pthw.domain.DispatcherProvider
import com.pthw.domain.repository.BiometricRepository
import com.pthw.domain.usecase.utils.CoroutineUseCase
import com.pthw.domain.usecase.utils.TwoParams
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