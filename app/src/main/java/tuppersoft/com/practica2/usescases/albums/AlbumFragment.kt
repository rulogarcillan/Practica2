package tuppersoft.com.practica2.usescases.albums

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list.view.*
import tuppersoft.com.data.Repository
import tuppersoft.com.data.connection.ResponseCallback
import tuppersoft.com.domain.dbo.DialogData
import tuppersoft.com.domain.dto.Album
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.dialogs.DialogActions
import tuppersoft.com.practica2.extensions.changeVisibility
import tuppersoft.com.practica2.extensions.showDialog
import tuppersoft.com.practica2.usescases.global.ALBUM
import tuppersoft.com.practica2.usescases.global.ERR_CONECTION
import tuppersoft.com.practica2.usescases.global.GlobalActivity
import tuppersoft.com.practica2.usescases.global.GlobalListener
import tuppersoft.com.practica2.usescases.main.MainPlaceHolderFragment
import tuppersoft.com.practica2.usescases.photos.PhotosActivity


class AlbumFragment : MainPlaceHolderFragment(), GlobalListener, DialogActions {

    lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_list, container, false)
        // setHasOptionsMenu(true)
        rootView.idListRecyclerView.layoutManager = LinearLayoutManager(activity)
        rootView.idListRecyclerView.adapter = AlbumAdapter(null, this)
        getAlbum()
        return rootView
    }

    private fun getAlbum() {
        rootView.idProgressBar.changeVisibility(View.VISIBLE)

        Repository.getAlbums(object : ResponseCallback<MutableList<Album>> {
            override fun onResponse(response: MutableList<Album>) {
                (rootView.idListRecyclerView.adapter as AlbumAdapter).addItems(response)
                rootView.idProgressBar.changeVisibility(View.GONE)
            }

            override fun onFailure(t: Throwable) {
                rootView.idProgressBar.changeVisibility(View.GONE)
                this@AlbumFragment.childFragmentManager.showDialog(
                    DialogData(
                        getString(R.string.failed),
                        getString(R.string.error_conection),
                        getString(R.string.cancel),
                        getString(R.string.retry),
                        ERR_CONECTION
                    )
                )
            }
        })
    }

    override fun <T> onClick(item: T) {
        startComments(item as Album)
    }

    private fun startComments(item: Album) {
        val intent = Intent(activity, PhotosActivity::class.java)
        intent.putExtra(ALBUM, item)
        startActivity(intent)
    }

    override fun buttonPositive(requestCode: Int) {
        when (requestCode) {
            ERR_CONECTION -> getAlbum()
            else -> (requireActivity() as GlobalActivity).buttonPositive(requestCode)
        }
    }

    override fun buttonNegative(requestCode: Int) {
        (requireActivity() as GlobalActivity).buttonPositive(requestCode)
    }
}

