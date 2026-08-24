package org.montra.crudmuliplatform

import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val hostUrl: String = "http://10.0.2.2:8000"
}

actual fun getPlatform(): Platform = AndroidPlatform()