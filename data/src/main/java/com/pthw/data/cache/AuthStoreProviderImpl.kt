package com.pthw.data.cache

import android.content.Context
import androidx.core.content.edit
import com.pthw.domain.repository.AuthStoreProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AuthStoreProviderImpl @Inject constructor(
    @ApplicationContext context: Context
) : AuthStoreProvider {
    private val sharedPreference by lazy {
        SecureSharedPreference.get(context, PREF_NAME)
    }

    companion object {
        private const val PREF_NAME = "AUTH_TOKEN"
        private const val KEY_AUTH_TOKEN = "auth_token"
    }

    override fun storeAuthToken(value: String) {
        sharedPreference.edit {
            putString(KEY_AUTH_TOKEN, value)
        }
    }

    override fun getAuthToken() = sharedPreference.getString(KEY_AUTH_TOKEN, null)

    override fun clearAuthToken() {
        sharedPreference.edit {
            remove(KEY_AUTH_TOKEN)
        }
    }
}
