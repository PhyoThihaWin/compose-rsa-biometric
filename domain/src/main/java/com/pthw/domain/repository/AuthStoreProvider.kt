package com.pthw.domain.repository

interface AuthStoreProvider {
    fun storeAuthToken(value: String)
    fun getAuthToken(): String?
    fun clearAuthToken()
}

