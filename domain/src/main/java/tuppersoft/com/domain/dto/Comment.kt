package tuppersoft.com.domain.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comment(
    val postId: Long,
    val id: Long,
    val name: String,
    val email: String,
    val body: String
) : Parcelable