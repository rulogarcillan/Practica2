package tuppersoft.com.practica2.extensions

import android.util.Log
import tuppersoft.com.practica2.BuildConfig
import tuppersoft.com.practica2.usescases.global.TAG


fun String?.log(tag: String = TAG) {
    if (BuildConfig.DEBUG) {
        Log.d(tag, this ?: "Null value")
    }
}
