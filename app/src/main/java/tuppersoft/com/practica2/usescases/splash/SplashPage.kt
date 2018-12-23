package tuppersoft.com.practica2.usescases.splash

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class SplashPage(val title: String, val body: String, val image: Int, val anim: Int? = null) : Parcelable