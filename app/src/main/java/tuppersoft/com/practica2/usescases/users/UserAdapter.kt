package tuppersoft.com.practica2.usescases.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import tuppersoft.com.domain.dto.User
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.databinding.UserItemBinding
import tuppersoft.com.practica2.usescases.global.GlobalAdapter
import tuppersoft.com.practica2.usescases.global.GlobalListener

class UserAdapter(list: MutableList<User>?, val listener: GlobalListener) : GlobalAdapter<User>(list) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: UserItemBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.user_item, parent, false)

        return UserHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserHolder).bind(getItem(position), listener)
    }
}
