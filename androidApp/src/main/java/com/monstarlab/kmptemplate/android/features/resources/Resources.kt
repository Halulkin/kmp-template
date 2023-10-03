package com.monstarlab.kmptemplate.android.features.resources

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

class Resources : Screen {
    @Composable
    override fun Content() {
        ResourcesRoute()
    }
}