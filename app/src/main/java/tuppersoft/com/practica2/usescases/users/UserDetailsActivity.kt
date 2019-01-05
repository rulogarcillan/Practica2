package tuppersoft.com.practica2.usescases.users

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.activity_user_details.*
import tuppersoft.com.domain.dto.User
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.databinding.ActivityUserDetailsBinding
import tuppersoft.com.practica2.usescases.global.GlobalActivity
import tuppersoft.com.practica2.usescases.global.USER

class UserDetailsActivity : GlobalActivity() {

    private lateinit var binding: ActivityUserDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_details)
        getBundle()
        initActionBar()
    }

    private fun initActionBar() {
        toolbar.title = binding.user?.name ?: getString(R.string.user_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }
    }

    private fun getBundle() {
        binding.user = intent.getParcelableExtra(USER) as User
    }

}