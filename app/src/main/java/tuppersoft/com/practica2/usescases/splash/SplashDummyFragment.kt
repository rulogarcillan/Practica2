package tuppersoft.com.practica2.usescases.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.dummy_fragment.view.*
import tuppersoft.com.practica2.R

class SplashDummyFragment : Fragment() {

    lateinit var page: SplashPage

    companion object {
        const val DATA_SPLASH = "dataSplash"
        fun newInstance(data: SplashPage): SplashDummyFragment {
            val args = Bundle()
            args.putParcelable(DATA_SPLASH, data)
            val myFragment = SplashDummyFragment()
            myFragment.arguments = args
            return myFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dummy_fragment, container, false)
        getBundle(arguments)
        view.idIcon.setImageResource(page.image)
        view.idTitle.text = page.title
        view.idBody.text = page.body
        if (page.anim != null) {
            initAnim(view, page.anim!!)
        }
        return view
    }

    private fun getBundle(savedInstanceState: Bundle?) {
        page = savedInstanceState?.getParcelable(DATA_SPLASH) ?: SplashPage("", "", 0)
    }

    private fun initAnim(view: View, anim: Int) {
        val vibrate = AnimationUtils.loadAnimation(context, anim)
        view.idIcon.animation = vibrate
        vibrate.start()
    }

}
