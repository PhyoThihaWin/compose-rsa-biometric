package com.pthw.biometricwithasymmetric.core.domain.usecase

import com.pthw.biometricwithasymmetric.appbase.DispatcherProvider
import com.pthw.biometricwithasymmetric.core.domain.repository.BiometricRepository
import com.pthw.biometricwithasymmetric.core.domain.usecase.utils.CoroutineUseCase
import javax.inject.Inject

/**
 * Created by P.T.H.W on 11/03/2024.
 */
class GetChallengeUseCase @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val repository: BiometricRepository
) : CoroutineUseCase<String, String>(dispatcherProvider) {
    override suspend fun provide(params: String): String {
        return repository.getChallenge(params)
    }

}