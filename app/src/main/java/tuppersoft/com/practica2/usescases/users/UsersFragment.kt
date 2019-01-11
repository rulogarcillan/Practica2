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
import tuppersoft.com.domain.dbo.DialogData
import tuppersoft.com.domain.dto.User
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.dialogs.DialogActions
import tuppersoft.com.practica2.extensions.changeVisibility
import tuppersoft.com.practica2.extensions.showDialog
import tuppersoft.com.practica2.usescases.global.ERR_CONECTION
import tuppersoft.com.practica2.usescases.global.GlobalActivity
import tuppersoft.com.practica2.usescases.global.GlobalListener
import tuppersoft.com.practica2.usescases.global.USER
import tuppersoft.com.practica2.usescases.main.MainPlaceHolderFragment


class UsersFragment : MainPlaceHolderFragment(), GlobalListener, DialogActions {

    lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_list, container, false)
        rootView.idListRecyclerView.layoutManager = LinearLayoutManager(activity)
        rootView.idListRecyclerView.adapter = UserAdapter(null, this)
        getUser()
        return rootView
    }

    private fun getUser() {
        rootView.idProgressBar.changeVisibility(View.VISIBLE)

        Repository.getUsers(object : ResponseCallback<MutableList<User>> {
            override fun onResponse(response: MutableList<User>) {
                (rootView.idListRecyclerView.adapter as UserAdapter).addItems(response)

                rootView.idProgressBar.changeVisibility(View.GONE)
            }

            override fun onFailure(t: Throwable) {
                rootView.idProgressBar.changeVisibility(View.GONE)
                this@UsersFragment.childFragmentManager.showDialog(
                    DialogData(
                        getString(R.string.failed),
                        getString(R.string.error_conection),
                        getString(R.string.cancel),
                        getString(R.string.retry),
                        ERR_CONECTION
                    )
                )
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

    override fun buttonPositive(requestCode: Int) {
        when (requestCode) {
            ERR_CONECTION -> getUser()
            else -> (requireActivity() as GlobalActivity).buttonPositive(requestCode)
        }
    }

    override fun buttonNegative(requestCode: Int) {
        (requireActivity() as GlobalActivity).buttonPositive(requestCode)
    }


}

