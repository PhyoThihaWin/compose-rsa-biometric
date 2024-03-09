package com.pthw.biometricwithasymmetric.core.data.repository

import com.pthw.biometricwithasymmetric.core.data.network.biometric.BiometricService
import com.pthw.biometricwithasymmetric.core.domain.repository.BiometricRepository
import javax.inject.Inject

/**
 * Created by P.T.H.W on 05/03/2024.
 */
class BiometricRepositoryImpl @Inject constructor(
    val service: BiometricService
) : BiometricRepository {
    override suspend fun createBiometric(deviceId: String, publicKey: String): String {
        return service.createBiometric(deviceId, publicKey)
    }

    override suspend fun getChallenge(deviceId: String): String {
        return service.getChallenge(deviceId)
    }

    override suspend fun validateSignature(biometricId: String, signature: String): String {
        return service.validateSignature(biometricId, signature)
    }
}