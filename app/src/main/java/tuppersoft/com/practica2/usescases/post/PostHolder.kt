package tuppersoft.com.practica2.usescases.post

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_item.view.*
import tuppersoft.com.domain.dto.Post

class PostHolder(private val rootView: View) : RecyclerView.ViewHolder(rootView) {

    fun bind(item: Post) {

        rootView.idTitle.text = item.title
        rootView.idBody.text = item.body
        rootView.idPost.text = "#" + item.id.toString()
        rootView.idUser.text = item.userID.toString()

    }


}