package tuppersoft.com.domain.dbo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DialogData(
    val tittle: String,
    val body: String,
    val negativeButton: String?,
    val positiveButton: String,
    val requestCode: Int = -1
) :
    Parcelable