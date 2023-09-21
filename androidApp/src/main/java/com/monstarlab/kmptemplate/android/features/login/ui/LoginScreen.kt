package com.monstarlab.kmptemplate.android.features.login.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.monstarlab.kmptemplate.android.core.ui.previews.LightDarkPreview
import com.monstarlab.kmptemplate.android.designsystem.components.AppTopBar
import com.monstarlab.kmptemplate.android.designsystem.components.appbutton.AppButton
import com.monstarlab.kmptemplate.android.designsystem.components.apptextfield.AppTextField
import com.monstarlab.kmptemplate.android.designsystem.theme.AppTheme
import com.monstarlab.kmptemplate.android.designsystem.theme.Theme
import com.monstarlab.kmptemplate.core.error.displayableMessage

@Composable
fun LoginScreen(
    state: LoginState = LoginState(),
    actions: LoginActions = LoginActions()
) = Scaffold(
    topBar = {
        AppTopBar(
            modifier = Modifier.systemBarsPadding(),
            title = "Login"
        )
    }
) {
    Column(
        modifier = Modifier
            .padding(it)
            .fillMaxSize()
            .padding(Theme.dimensions.big1)
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AppTextField(
            value = state.email,
            onValueChange = actions.onEmailChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = "E-Mail"
        )

        Spacer(modifier = Modifier.size(Theme.dimensions.medium3))

        AppTextField(
            value = state.password,
            onValueChange = actions.onPasswordChange,
            modifier = Modifier.fillMaxWidth(),
            error = state.error?.displayableMessage,
            visualTransformation = PasswordVisualTransformation(),
            placeholder = "Password"
        )
        Spacer(modifier = Modifier.size(Theme.dimensions.medium3))

        AppButton(
            text = "Login",
            onClick = actions.onLoginClick,
            modifier = Modifier.fillMaxWidth(),
            isLoading = state.isLoading
        )
    }
}

@Composable
@LightDarkPreview
private fun LoginScreenPreview() {
    AppTheme {
        LoginScreen()
    }
}
