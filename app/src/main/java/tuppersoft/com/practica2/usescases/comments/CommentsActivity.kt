package tuppersoft.com.practica2.usescases.comments

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_comments.*
import kotlinx.android.synthetic.main.appbar.*
import tuppersoft.com.data.Repository
import tuppersoft.com.data.connection.ResponseCallback
import tuppersoft.com.domain.dbo.DialogData
import tuppersoft.com.domain.dto.Comment
import tuppersoft.com.domain.dto.Post
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.databinding.ActivityCommentsBinding
import tuppersoft.com.practica2.extensions.changeVisibility
import tuppersoft.com.practica2.extensions.showDialog
import tuppersoft.com.practica2.usescases.global.ERR_CONECTION
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

        idProgressBar.changeVisibility(View.VISIBLE)
        Repository.getComments((binding.post as Post).id, object : ResponseCallback<MutableList<Comment>> {
            override fun onResponse(response: MutableList<Comment>) {
                idProgressBar.changeVisibility(View.GONE)
                (idRecyclerViewComments.adapter as CommentsAdapter).addItems(response)
            }

            override fun onFailure(t: Throwable) {
                idProgressBar.changeVisibility(View.GONE)
                this@CommentsActivity.supportFragmentManager.showDialog(
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

    private fun getBundle() {
        binding.post = intent.getParcelableExtra(POST) as Post
    }


    override fun buttonPositive(requestCode: Int) {
        when (requestCode) {
            ERR_CONECTION -> getComments()
            else -> super.buttonPositive(requestCode)
        }
    }
}
