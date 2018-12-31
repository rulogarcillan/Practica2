package tuppersoft.com.practica2.usescases.post

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_item.view.*
import tuppersoft.com.data.Repository
import tuppersoft.com.data.connection.ResponseCallback
import tuppersoft.com.domain.dto.Post
import tuppersoft.com.domain.dto.User
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.usescases.global.GlobalListener

class PostHolder(private val rootView: View) : RecyclerView.ViewHolder(rootView) {

    fun bind(item: Post, listener: GlobalListener) {

        rootView.idTitle.text = item.title
        rootView.idBody.text = item.body
        rootView.idPost.text = String.format(rootView.context.getString(R.string.id_post), item.id.toString())
        getUser(item.userId)

        rootView.idItem.setOnClickListener { listener.onClick(item) }
    }

    private fun getUser(userId: Long) {

        Repository.getUser(userId, object : ResponseCallback<User> {
            override fun onResponse(response: User) {
                rootView.idUser.visibility = View.VISIBLE
                rootView.idUser.text =
                        String.format(rootView.context.getString(R.string.written_by), response.username, userId)
            }

            override fun onFailure(t: Throwable) {
                rootView.idUser.visibility = View.GONE
            }
        })
    }


}