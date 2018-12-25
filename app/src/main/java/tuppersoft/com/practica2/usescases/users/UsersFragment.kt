package tuppersoft.com.practica2.usescases.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.usescases.main.MainPlaceHolderFragment

class UsersFragment : MainPlaceHolderFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_users, container, false)
        return view
    }
}

