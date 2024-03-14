package com.pthw.domain.usecase

import com.pthw.domain.DispatcherProvider
import com.pthw.domain.repository.BiometricRepository
import com.pthw.domain.usecase.utils.CoroutineUseCase
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