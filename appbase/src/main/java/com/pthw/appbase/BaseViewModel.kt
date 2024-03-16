package com.pthw.appbase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pthw.appbase.exceptionmapper.ExceptionHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {
    @Inject
    protected lateinit var exception: ExceptionHandler

    fun catch(job: (code: Int?, msg: String?) -> Unit = { _, _ -> }): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, throwable ->
            viewModelScope.launch {
                Timber.e(throwable)

                if (::exception.isInitialized) {
                    job(exception.getCode(throwable), exception.getErrorBody(throwable))
                }

            }
        }
    }

    val hander = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
        throw throwable
    }

}

