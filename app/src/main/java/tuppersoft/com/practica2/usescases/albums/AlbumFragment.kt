package tuppersoft.com.practica2.usescases.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import tuppersoft.com.data.Repository
import tuppersoft.com.data.connection.ResponseCallback
import tuppersoft.com.domain.dto.Album
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.usescases.global.GlobalListener
import tuppersoft.com.practica2.usescases.main.MainPlaceHolderFragment
import tuppersoft.com.practica2.usescases.post.PostAdapter


class AlbumFragment : MainPlaceHolderFragment(), GlobalListener {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_list, container, false)
       // setHasOptionsMenu(true)
        rootView.idListRecyclerView.layoutManager = LinearLayoutManager(activity)
        rootView.idListRecyclerView.adapter = AlbumAdapter(null, this)
        getAlbum(rootView)
        return rootView
    }

    private fun getAlbum(view: View) {
        view.idProgressBar.visibility = View.VISIBLE

        Repository.getAlbums(object : ResponseCallback<MutableList<Album>> {
            override fun onResponse(response: MutableList<Album>) {
                (view.idListRecyclerView.adapter as AlbumAdapter).addItems(response)
                idProgressBar.visibility = View.GONE
            }

            override fun onFailure(t: Throwable) {
                idProgressBar.visibility = View.GONE
            }
        })

    }

    override fun <T> onClick(item: T) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

