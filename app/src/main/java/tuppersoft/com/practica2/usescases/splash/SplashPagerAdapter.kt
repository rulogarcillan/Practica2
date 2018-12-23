package tuppersoft.com.practica2.usescases.splash

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class SplashPagerAdapter(fm: FragmentManager, private val list: ArrayList<SplashPage>) : FragmentStatePagerAdapter(fm) {

    override fun getCount() = list.size

    override fun getItem(position: Int): Fragment = SplashDummyFragment.newInstance(list[position])

}