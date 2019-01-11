package tuppersoft.com.practica2.usescases.users

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_user_details.*
import tuppersoft.com.domain.dto.User
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.databinding.ActivityUserDetailsBinding
import tuppersoft.com.practica2.usescases.global.GlobalActivity
import tuppersoft.com.practica2.usescases.global.USER

class UserDetailsActivity : GlobalActivity(), OnMapReadyCallback {


    private lateinit var binding: ActivityUserDetailsBinding
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_details)
        getBundle()
        initActionBar()
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(binding.user!!.address.geo.lat.toDouble(), binding.user!!.address.geo.lng.toDouble())

        mMap.addMarker(MarkerOptions().position(sydney).title(binding.user!!.address.city))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

}