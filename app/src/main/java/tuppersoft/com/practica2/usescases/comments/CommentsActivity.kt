package tuppersoft.com.practica2.usescases.comments

import android.os.Bundle
import kotlinx.android.synthetic.main.appbar.*
import tuppersoft.com.domain.dto.Post
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.extensions.log
import tuppersoft.com.practica2.usescases.global.GlobalActivity
import tuppersoft.com.practica2.usescases.global.POST


class CommentsActivity : GlobalActivity() {

    lateinit var post: Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)
        initActionBar()
        getBundle()
    }

    private fun initActionBar() {
        toolbar.title = getString(R.string.comments)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }
    }

    private fun getBundle() {
        post = intent.getParcelableExtra(POST) as Post
        post.title.log()
    }
}
