package com.pthw.biometricwithasymmetric.core.data.repository

import com.pthw.biometricwithasymmetric.core.data.network.biometric.BiometricService
import com.pthw.biometricwithasymmetric.core.domain.repository.AuthStoreProvider
import com.pthw.biometricwithasymmetric.core.domain.repository.BiometricRepository
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by P.T.H.W on 05/03/2024.
 */
class BiometricRepositoryImpl @Inject constructor(
    private val service: BiometricService,
    private val authStoreProvider: AuthStoreProvider,
) : BiometricRepository {
    override suspend fun createBiometric(deviceId: String, publicKey: String): String {
        val data = service.createBiometric(deviceId, publicKey)
        authStoreProvider.storeAuthToken(data.token)
        return data.data.biometricId
    }

    override suspend fun getChallenge(deviceId: String): String {
        return service.getChallenge(deviceId)
    }

    override suspend fun validateSignature(biometricId: String, signature: String): String {
        val raw =  service.validateSignature(biometricId, signature)
        Timber.w("Reached success!! + $raw")
        return raw
    }
}