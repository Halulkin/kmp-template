package com.monstarlab.kmptemplate.android

import android.annotation.SuppressLint
import android.app.Application
import com.monstarlab.kmptemplate.android.di.androidModules
import com.monstarlab.kmptemplate.android.features.nstack.Translation
import com.monstarlab.kmptemplate.di.sharedModules
import dk.nodes.nstack.kotlin.NStack
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    @SuppressLint("AppOpenMissing")
    override fun onCreate() {
        super.onCreate()
        NStack.translationClass = Translation::class.java
        NStack.init(this, BuildConfig.DEBUG)

        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(sharedModules() + androidModules())
        }
    }
}