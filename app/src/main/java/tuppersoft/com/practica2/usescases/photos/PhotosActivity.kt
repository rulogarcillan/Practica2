package tuppersoft.com.practica2.usescases.photos

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.appbar.*
import tuppersoft.com.data.Repository
import tuppersoft.com.data.connection.ResponseCallback
import tuppersoft.com.domain.dto.Album
import tuppersoft.com.domain.dto.Photo
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.usescases.global.ALBUM
import tuppersoft.com.practica2.usescases.global.GlobalActivity


class PhotosActivity : GlobalActivity() {

    private lateinit var album: Album

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        idListRecyclerView.layoutManager = GridLayoutManager(this, 5)
        idListRecyclerView.adapter = PhotosAdapter(null)

        initActionBar()
        getBundle()
        getPhotos()
    }

    private fun initActionBar() {
        toolbar.title = getString(R.string.photos)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }
    }


    private fun getPhotos() {
        idProgressBar.visibility = View.VISIBLE
        Repository.getPhotos(album.id, object : ResponseCallback<MutableList<Photo>> {
            override fun onResponse(response: MutableList<Photo>) {
                idProgressBar.visibility = View.GONE
                (idListRecyclerView.adapter as PhotosAdapter).addItems(response)
            }

            override fun onFailure(t: Throwable) {
                idProgressBar.visibility = View.GONE
            }

        })
    }

    private fun getBundle() {
        album = intent.getParcelableExtra(ALBUM) as Album
    }
}
