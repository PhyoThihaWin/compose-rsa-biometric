package com.pthw.biometricwithasymmetric.appbase

import androidx.lifecycle.ViewModel
import com.pthw.biometricwithasymmetric.appbase.exceptionmapper.ExceptionMapper
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {
    @Inject
    protected lateinit var exception: ExceptionMapper
}