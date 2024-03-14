package com.pthw.domain.repository

/**
 * Created by P.T.H.W on 05/03/2024.
 */
interface BiometricRepository {
    suspend fun createBiometric(deviceId: String, publicKey: String): String
    suspend fun getChallenge(deviceId: String): String
    suspend fun validateSignature(biometricId: String, signature: String): String
}