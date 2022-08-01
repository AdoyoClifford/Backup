package com.example.medicalemergency.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.medicalemergency.common.composable.*
import com.example.medicalemergency.common.ext.basicButton
import com.example.medicalemergency.common.ext.fieldModifier
import com.example.medicalemergency.common.ext.textButton


@Composable
fun LoginScreen(
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    BasicToolbar("")

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailField(uiState.email, viewModel::onEmailChange, Modifier.fieldModifier())
        PasswordField(uiState.password, viewModel::onPasswordChange, Modifier.fieldModifier())

        BasicButton("sign_in", Modifier.basicButton()) {
            viewModel.onSignInClick(openAndPopUp)
        }

        BasicTextButton(text ="AppText.forgot_password", Modifier.textButton()) {
            viewModel.onForgotPasswordClick()
        }
    }
}
