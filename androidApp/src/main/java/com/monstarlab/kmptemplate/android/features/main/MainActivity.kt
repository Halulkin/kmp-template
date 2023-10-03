package com.monstarlab.kmptemplate.android.features.main

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import cafe.adriel.voyager.navigator.Navigator
import com.monstarlab.kmptemplate.android.designsystem.theme.AppTheme
import com.monstarlab.kmptemplate.android.features.login.ui.Login
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by inject()
    private val notificationRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { viewModel.stateFlow.value.showSplash }
        WindowCompat.setDecorFitsSystemWindows(window, false)
        checkNotificationPermission()
        observeState()
        content()
    }

    private fun content() {
        setContent {
            AppTheme {
                Navigator(Login())
            }
        }
    }

    private fun checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            notificationRequest.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private fun observeState() = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.stateFlow.collect {
                handleNstackData(it.nStackData, viewModel::onNstackDataConsumed)
            }
        }
    }
}