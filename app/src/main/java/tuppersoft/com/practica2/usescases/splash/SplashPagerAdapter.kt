package tuppersoft.com.practica2.usescases.splash

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class SplashPagerAdapter(fm: FragmentManager, private val numPages: Int) : FragmentStatePagerAdapter(fm) {

    override fun getCount() =  numPages

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> SplashTypeOneFragment.newInstance()
            1 -> SplashTypeOneFragment.newInstance()
            2 -> SplashTypeOneFragment.newInstance()
            else -> {
                SplashTypeOneFragment.newInstance()


            }
        }
}