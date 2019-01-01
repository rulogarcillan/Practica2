package tuppersoft.com.practica2.usescases.comments

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_comments.*
import kotlinx.android.synthetic.main.appbar.*
import tuppersoft.com.data.Repository
import tuppersoft.com.data.connection.ResponseCallback
import tuppersoft.com.domain.dto.Comment
import tuppersoft.com.domain.dto.Post
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.databinding.ActivityCommentsBinding
import tuppersoft.com.practica2.usescases.global.GlobalActivity
import tuppersoft.com.practica2.usescases.global.POST


class CommentsActivity : GlobalActivity() {

    private lateinit var binding: ActivityCommentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_comments)

        idRecyclerViewComments.layoutManager = LinearLayoutManager(this)
        idRecyclerViewComments.adapter = CommentsAdapter(null)

        initActionBar()
        getBundle()
        getComments()
    }

    private fun initActionBar() {
        toolbar.title = getString(R.string.comments)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }
    }


    private fun getComments() {
        Repository.getComments((binding.post as Post).id, object : ResponseCallback<MutableList<Comment>> {
            override fun onResponse(response: MutableList<Comment>) {
                (idRecyclerViewComments.adapter as CommentsAdapter).addItems(response)
            }

            override fun onFailure(t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    private fun getBundle() {
        binding.post = intent.getParcelableExtra(POST) as Post
    }
}
