package tuppersoft.com.practica2.usescases.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.usescases.main.MainPlaceHolderFragment

class PostFragment : MainPlaceHolderFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_post, container, false)
        return view
    }
}
