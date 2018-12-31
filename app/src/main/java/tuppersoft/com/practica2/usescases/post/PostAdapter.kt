package tuppersoft.com.practica2.usescases.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tuppersoft.com.domain.dto.Post
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.usescases.global.GlobalAdapter
import tuppersoft.com.practica2.usescases.global.GlobalListener

class PostAdapter(list: MutableList<Post>?, val listener : GlobalListener) : GlobalAdapter<Post>(list) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return PostHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.post_item,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PostHolder).bind(getItem(position), listener)
    }
}
