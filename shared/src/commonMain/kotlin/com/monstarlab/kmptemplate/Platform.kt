package com.monstarlab.kmptemplate

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform