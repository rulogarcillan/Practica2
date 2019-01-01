package tuppersoft.com.practica2.usescases.comments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import tuppersoft.com.domain.dto.Comment
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.databinding.CommentItemBinding
import tuppersoft.com.practica2.usescases.global.GlobalAdapter


class CommentsAdapter(var comments: MutableList<Comment>?) : GlobalAdapter<Comment>(comments) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var binding: CommentItemBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.comment_item, parent, false)

        return CommentHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CommentHolder).bind(getItem(position))
    }
}