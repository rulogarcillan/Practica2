package tuppersoft.com.practica2.usescases.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tuppersoft.com.domain.dto.Photo
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.usescases.global.GlobalAdapter


class PhotosAdapter(var photos: MutableList<Photo>?) : GlobalAdapter<Photo>(photos) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return PhotosHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.photo_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PhotosHolder).bind(getItem(position))
    }
}