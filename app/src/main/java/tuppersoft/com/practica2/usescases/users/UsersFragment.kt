package tuppersoft.com.practica2.usescases.users


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list.view.*
import tuppersoft.com.data.Repository
import tuppersoft.com.data.connection.ResponseCallback
import tuppersoft.com.domain.dto.User
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.extensions.changeVisibility
import tuppersoft.com.practica2.usescases.global.GlobalListener
import tuppersoft.com.practica2.usescases.global.USER
import tuppersoft.com.practica2.usescases.main.MainPlaceHolderFragment


class UsersFragment : MainPlaceHolderFragment(), GlobalListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_list, container, false)
        rootView.idListRecyclerView.layoutManager = LinearLayoutManager(activity)
        rootView.idListRecyclerView.adapter = UserAdapter(null, this)
        getUser(rootView)
        return rootView
    }

    private fun getUser(view: View) {
        view.idProgressBar.changeVisibility(View.VISIBLE)

        Repository.getUsers(object : ResponseCallback<MutableList<User>> {
            override fun onResponse(response: MutableList<User>) {
                (view.idListRecyclerView.adapter as UserAdapter).addItems(response)

                view.idProgressBar.changeVisibility(View.GONE)
            }

            override fun onFailure(t: Throwable) {
                view.idProgressBar.changeVisibility(View.GONE)
            }
        })
    }

    override fun <T> onClick(item: T) {
        startUserDetails(item as User)
    }

    private fun startUserDetails(item: User) {
        val intent = Intent(activity, UserDetailsActivity::class.java)
        intent.putExtra(USER, item)
        startActivity(intent)
    }

}

