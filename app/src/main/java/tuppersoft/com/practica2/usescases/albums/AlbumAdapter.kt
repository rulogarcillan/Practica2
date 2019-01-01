package tuppersoft.com.practica2.usescases.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import tuppersoft.com.domain.dto.Album
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.databinding.AlbumItemBinding
import tuppersoft.com.practica2.usescases.global.GlobalAdapter
import tuppersoft.com.practica2.usescases.global.GlobalListener

class AlbumAdapter(list: MutableList<Album>?, val listener: GlobalListener) : GlobalAdapter<Album>(list) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: AlbumItemBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.album_item, parent, false)

        return AlbumHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as AlbumHolder).bind(getItem(position), listener)
    }
}
