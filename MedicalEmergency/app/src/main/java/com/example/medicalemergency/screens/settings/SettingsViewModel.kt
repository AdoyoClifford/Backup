package com.example.medicalemergency.screens.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.makeitso.screens.MakeItSoViewModel
import com.example.medicalemergency.LOGIN_SCREEN
import com.example.medicalemergency.SIGN_UP_SCREEN
import com.example.medicalemergency.SPLASH_SCREEN
import com.example.medicalemergency.model.service.AccountService
import com.example.medicalemergency.model.service.LogService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    logService: LogService,
    private val accountService: AccountService,
) : MakeItSoViewModel(logService) {
    var uiState = mutableStateOf(SettingsUiState())
        private set

    fun initialize() {
        uiState.value = SettingsUiState(accountService.isAnonymousUser())
    }

    fun onLoginClick(openScreen: (String) -> Unit) = openScreen(LOGIN_SCREEN)

    fun onSignUpClick(openScreen: (String) -> Unit) = openScreen(SIGN_UP_SCREEN)

    fun onSignOutClick(restartApp: (String) -> Unit) {
        viewModelScope.launch(showErrorExceptionHandler) {
            accountService.signOut()
            restartApp(SPLASH_SCREEN)
        }
    }

//    fun onDeleteMyAccountClick(restartApp: (String) -> Unit) {
//        viewModelScope.launch(showErrorExceptionHandler) {
//            storageService.deleteAllForUser(accountService.getUserId()) { error ->
//                if (error == null) deleteAccount(restartApp) else onError(error)
//            }
//        }
//    }

    private fun deleteAccount(restartApp: (String) -> Unit) {
        viewModelScope.launch(showErrorExceptionHandler) {
            accountService.deleteAccount { error ->
                if (error == null) restartApp(SPLASH_SCREEN) else onError(error)
            }
        }
    }
}