package tuppersoft.com.practica2.usescases.albums

import androidx.recyclerview.widget.RecyclerView
import tuppersoft.com.domain.dto.Album
import tuppersoft.com.practica2.databinding.AlbumItemBinding
import tuppersoft.com.practica2.usescases.global.GlobalListener

class AlbumHolder(private val rootView: AlbumItemBinding) : RecyclerView.ViewHolder(rootView.root) {

    fun bind(item: Album, listener: GlobalListener) {
        rootView.album = item
        rootView.action = listener
    }
}