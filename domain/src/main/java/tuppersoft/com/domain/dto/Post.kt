package tuppersoft.com.domain.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
    val userID: Long,
    val id: Long,
    val title: String,
    val body: String
) : Parcelable