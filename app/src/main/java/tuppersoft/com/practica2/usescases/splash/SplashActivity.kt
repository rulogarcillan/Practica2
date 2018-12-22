package tuppersoft.com.practica2.usescases.splash

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import kotlinx.android.synthetic.main.activity_splash.*
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.usescases.global.GlobalActivity


class SplashActivity : GlobalActivity(), OnPageChangeListener {

    companion object {
        const val NUM_PAGES = 3
    }

    override fun onPageScrollStateChanged(state: Int) {
        return
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        return
    }

    override fun onPageSelected(position: Int) {
        if (position == NUM_PAGES - 1) {
            idGo.text = getString(R.string.go)
            idSkip.visibility = View.GONE
        } else {
            idGo.text = getString(R.string.next)
            idSkip.visibility = View.VISIBLE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initAdapter()
        initIndicator()
        setText()
    }

    private fun initIndicator() {
        tab_layout.setupWithViewPager(idViewPager, true)
    }

    private fun initAdapter() {
        idViewPager.adapter = SplashPagerAdapter(supportFragmentManager, NUM_PAGES)
        idViewPager.addOnPageChangeListener(this)
    }

    fun setText() {
        idGo.text = getString(R.string.next)
        idSkip.text = getString(R.string.skip)
    }

    fun onClickSkip(view: View) {
        idViewPager.currentItem = NUM_PAGES - 1
    }

    fun onClickNext(view: View) {
        if (idViewPager.currentItem == NUM_PAGES - 1) {
            Log.d("Rulo", "start activity")
        } else if (idViewPager.currentItem < NUM_PAGES - 1) {
            idViewPager.currentItem = idViewPager.currentItem + 1
        }
    }
}
