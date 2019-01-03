package tuppersoft.com.practica2.usescases.users

import android.os.Bundle
import kotlinx.android.synthetic.main.appbar.*
import tuppersoft.com.domain.dto.User
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.usescases.global.GlobalActivity
import tuppersoft.com.practica2.usescases.global.USER

class UserDetailsActivity : GlobalActivity() {

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        initActionBar()
        getBundle()

    }

    private fun initActionBar() {
        toolbar.title = getString(R.string.user_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }
    }


    private fun getBundle() {
        user = intent.getParcelableExtra(USER) as User
    }

}