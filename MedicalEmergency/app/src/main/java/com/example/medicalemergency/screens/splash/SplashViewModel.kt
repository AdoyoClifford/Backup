package com.example.medicalemergency.screens.splash

import androidx.lifecycle.viewModelScope
import com.example.medicalemergency.SPLASH_SCREEN
import com.example.medicalemergency.TASKS_SCREEN
import com.example.makeitso.screens.MakeItSoViewModel
import com.example.medicalemergency.model.service.AccountService
import com.example.medicalemergency.model.service.LogService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountService: AccountService,
    private val logService: LogService
) : MakeItSoViewModel(logService) {
    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        if (accountService.hasUser()) openAndPopUp(TASKS_SCREEN, SPLASH_SCREEN)
        else createAnonymousAccount(openAndPopUp)
    }

    private fun createAnonymousAccount(openAndPopUp: (String, String) -> Unit) {
        viewModelScope.launch(logErrorExceptionHandler) {
            accountService.createAnonymousAccount { error ->
                if (error != null) logService.logNonFatalCrash(error)
                else openAndPopUp(TASKS_SCREEN, SPLASH_SCREEN)
            }
        }
    }
}