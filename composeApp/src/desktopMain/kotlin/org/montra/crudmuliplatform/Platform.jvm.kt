package org.montra.crudmuliplatform

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
    override val hostUrl: String = "http://localhost:8000"
}

actual fun getPlatform(): Platform = JVMPlatform()