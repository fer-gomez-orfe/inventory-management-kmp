package org.montra.crudmuliplatform

interface Platform {
    val name: String
    val hostUrl: String
}

expect fun getPlatform(): Platform