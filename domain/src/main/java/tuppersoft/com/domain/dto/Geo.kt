package tuppersoft.com.domain.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Geo(
    val lat: String,
    val lng: String
) : Parcelable
