package tuppersoft.com.practica2.usescases.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_post.view.*
import tuppersoft.com.data.Repository
import tuppersoft.com.data.connection.ResponseCallback
import tuppersoft.com.domain.dto.Post
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.usescases.main.MainPlaceHolderFragment


class PostFragment : MainPlaceHolderFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_post, container, false)

        rootView.idListRecyclerView.layoutManager = LinearLayoutManager(activity)
        rootView.idListRecyclerView.adapter = PostAdapter(null)
        getPost(rootView)
        return rootView
    }

    private fun getPost(rootView: View) {

        Repository.getPost(object : ResponseCallback<MutableList<Post>> {
            override fun onResponse(response: MutableList<Post>) {
                (rootView.idListRecyclerView.adapter as PostAdapter).addItems(response)
            }

            override fun onFailure(t: Throwable) {

            }
        })
    }


}

