package tuppersoft.com.practica2.usescases.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.post_fragment.view.*
import tuppersoft.com.practica2.R


class SplashTypeOneFragment : Fragment() {

    companion object {
        fun newInstance(): SplashTypeOneFragment = SplashTypeOneFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.post_fragment, container, false)
        initAnim(view)
        return view
    }


    fun initAnim(view: View) {
        val vibrate = AnimationUtils.loadAnimation(context, R.anim.vibrate)
        view.idBubble.animation = vibrate
        vibrate.start()
    }

}
