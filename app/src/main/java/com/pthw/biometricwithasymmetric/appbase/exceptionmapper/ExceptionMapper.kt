package com.pthw.biometricwithasymmetric.appbase.exceptionmapper

import com.pthw.biometricwithasymmetric.appbase.mapper.UnidirectionalSuspendMap


interface ExceptionMapper : UnidirectionalSuspendMap<Throwable, String> {
    fun getCode(item: Throwable): Int
    suspend fun getErrorBody(item: Throwable): String?
}
