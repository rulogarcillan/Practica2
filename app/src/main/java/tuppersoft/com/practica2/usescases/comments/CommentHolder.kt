package tuppersoft.com.practica2.usescases.comments

import androidx.recyclerview.widget.RecyclerView
import tuppersoft.com.domain.dto.Comment
import tuppersoft.com.practica2.databinding.CommentItemBinding


class CommentHolder(private val rootView: CommentItemBinding) : RecyclerView.ViewHolder(rootView.root) {

    fun bind(comment: Comment) {

        rootView.comment = comment
    }

}