package com.pthw.biometricwithasymmetric.core.domain.repository

interface AuthStoreProvider {
    fun storeAuthToken(value: String)
    fun getAuthToken(): String?
    fun clearAuthToken()
}

