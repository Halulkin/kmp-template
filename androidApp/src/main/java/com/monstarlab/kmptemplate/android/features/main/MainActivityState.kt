package com.monstarlab.kmptemplate.android.features.main

import dk.nodes.nstack.kotlin.models.AppOpenData

data class MainActivityState(
    val showSplash: Boolean = true,
    val nStackData: AppOpenData? = null
)
