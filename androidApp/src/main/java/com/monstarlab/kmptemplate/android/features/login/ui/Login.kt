package com.monstarlab.kmptemplate.android.features.login.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

class Login: Screen {

    @Composable
    override fun Content() {
        LoginRoute()
    }
}