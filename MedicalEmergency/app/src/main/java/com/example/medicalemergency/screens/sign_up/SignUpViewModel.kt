package com.example.medicalemergency.screens.sign_up

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.medicalemergency.SETTINGS_SCREEN
import com.example.medicalemergency.SIGN_UP_SCREEN
import com.example.medicalemergency.common.ext.isValidEmail
import com.example.medicalemergency.common.ext.isValidPassword
import com.example.medicalemergency.common.ext.passwordMatches
import com.example.medicalemergency.common.snackbar.SnackbarManager
import com.example.makeitso.screens.MakeItSoViewModel
import com.example.medicalemergency.model.service.AccountService
import com.example.medicalemergency.model.service.LogService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService,
    private val logService: LogService
) : MakeItSoViewModel(logService) {
    var uiState = mutableStateOf(SignUpUiState())
        private set

    private val email get() = uiState.value.email
    private val password get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRepeatPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(repeatPassword = newValue)
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage("AppText.email_error")
            return
        }

        if (!password.isValidPassword()) {
            SnackbarManager.showMessage("AppText.password_error")
            return
        }

        if (!password.passwordMatches(uiState.value.repeatPassword)) {
            SnackbarManager.showMessage("AppText.password_match_error")
            return
        }

        viewModelScope.launch(showErrorExceptionHandler) {
            val oldUserId = accountService.getUserId()
            accountService.createAccount(email, password) { error ->
                if (error == null) {
                    linkWithEmail()
                    updateUserId(oldUserId, openAndPopUp)
                } else onError(error)
            }
        }
    }

    private fun linkWithEmail() {
        viewModelScope.launch(showErrorExceptionHandler) {
            accountService.linkAccount(email, password) { error ->
                if (error != null) logService.logNonFatalCrash(error)
            }
        }
    }

//    private fun updateUserId(oldUserId: String, openAndPopUp: (String, String) -> Unit) {
//        viewModelScope.launch(showErrorExceptionHandler) {
//            val newUserId = accountService.getUserId()
//
//            storageService.updateUserId(oldUserId, newUserId) { error ->
//                if (error != null) logService.logNonFatalCrash(error)
//                else openAndPopUp(SETTINGS_SCREEN, SIGN_UP_SCREEN)
//            }
//        }
//    }
}