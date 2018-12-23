package tuppersoft.com.practica2.usescases.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import kotlinx.android.synthetic.main.activity_splash.*
import tuppersoft.com.data.Repository
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.usescases.global.GlobalActivity
import tuppersoft.com.practica2.usescases.main.MainActivity


class SplashActivity : GlobalActivity(), OnPageChangeListener {

    companion object {
        const val FIRST_TIME = "FIRST_TIME"
    }


    lateinit var pages: ArrayList<SplashPage>


    //region Listener PageChange
    override fun onPageScrollStateChanged(state: Int) {
        return
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        return
    }

    override fun onPageSelected(position: Int) {
        manageFinalSplash(position)
    }
    //endregion


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isFirstTime()
        setContentView(R.layout.activity_splash)
        createPages()
        initAdapter()
        initIndicator()
        if (::pages.isInitialized) {
            manageFinalSplash(0)
        }
    }


    fun onClickSkip(view: View) {
        idViewPager.currentItem = idViewPager.adapter?.count?.minus(1) ?: idViewPager.currentItem
    }

    fun onClickNext(view: View) {
        if (idViewPager.currentItem == idViewPager.adapter?.count?.minus(1) ?: idViewPager.currentItem) {
           launchActivityMain()
        } else if (idViewPager.currentItem < pages.size - 1) {
            idViewPager.currentItem = idViewPager.currentItem + 1
        }
    }

    private fun isFirstTime() {
        if (Repository.loadPreference(this, FIRST_TIME, true) as Boolean) {
            Repository.savePreference(this, FIRST_TIME, false)
        } else {
            launchActivityMain()
        }
    }

    private fun launchActivityMain() {
        val i = Intent(baseContext, MainActivity::class.java)
        startActivity(i)
        finish()
    }

    private fun manageFinalSplash(position: Int) {
        idSkip.text = getString(R.string.skip)
        if (position == idViewPager.adapter?.count?.minus(1) ?: idViewPager.currentItem) {
            idGo.text = getString(R.string.go)
            idSkip.visibility = View.GONE
        } else {
            idGo.text = getString(R.string.next)
            idSkip.visibility = View.VISIBLE
        }
    }

    private fun createPages() {
        pages = ArrayList()
        pages.add(
            SplashPage(
                getString(R.string.tittle_post),
                getString(R.string.body_post),
                R.drawable.ic_blabla,
                R.anim.fadein
            )
        )
        pages.add(
            SplashPage(
                getString(R.string.tittle_albums),
                getString(R.string.body_albums),
                R.drawable.ic_cassette,
                R.anim.vibrate
            )
        )
        pages.add(
            SplashPage(
                getString(R.string.tittle_users),
                getString(R.string.body_users),
                R.drawable.ic_users
            )
        )
    }

    private fun initIndicator() {
        tab_layout.setupWithViewPager(idViewPager, true)
    }

    private fun initAdapter() {
        idViewPager.adapter = SplashPagerAdapter(supportFragmentManager, pages)
        idViewPager.addOnPageChangeListener(this)
    }

}
