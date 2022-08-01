package com.example.medicalemergency

import androidx.lifecycle.ViewModel
import com.example.medicalemergency.common.snackbar.SnackbarManager
import com.example.medicalemergency.common.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import com.example.medicalemergency.model.service.LogService
import kotlinx.coroutines.CoroutineExceptionHandler

open class MedicalViewModel(private val logService: LogService): ViewModel() {
    open val showErrorExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }

    open val logErrorExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        logService.logNonFatalCrash(throwable)
    }

    open fun onError(error: Throwable) {
        SnackbarManager.showMessage(error.toSnackbarMessage())
        logService.logNonFatalCrash(error)
    }
}