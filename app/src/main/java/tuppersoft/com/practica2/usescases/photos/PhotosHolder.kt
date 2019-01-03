package tuppersoft.com.practica2.usescases.photos

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.photo_item.view.*
import tuppersoft.com.domain.dto.Photo
import tuppersoft.com.practica2.extensions.loadFromUrl


class PhotosHolder(private val rootView: View) : RecyclerView.ViewHolder(rootView) {

    fun bind(photo: Photo) {
        rootView.idPhoto.loadFromUrl(photo.thumbnailUrl)
    }
}