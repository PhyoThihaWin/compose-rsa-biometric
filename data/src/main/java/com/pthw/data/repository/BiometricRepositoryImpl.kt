package com.pthw.data.repository

import com.pthw.data.network.biometric.BiometricService
import com.pthw.domain.repository.AuthStoreProvider
import com.pthw.domain.repository.BiometricRepository
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
        Timber.w("BiometricId: ${data.data.biometricId}")
        return data.data.biometricId
    }

    override suspend fun getChallenge(deviceId: String): String {
        val data = service.getChallenge(deviceId)
        Timber.w("Challenge: $data")
        return data
    }

    override suspend fun validateSignature(biometricId: String, signature: String): String {
        val raw = service.validateSignature(biometricId, signature)
        return raw
    }
}