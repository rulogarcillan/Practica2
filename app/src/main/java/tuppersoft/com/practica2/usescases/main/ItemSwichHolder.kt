package tuppersoft.com.practica2.usescases.main

import android.view.View
import kotlinx.android.synthetic.main.nav_swich_main.view.*
import tuppersoft.com.practica2.R


class ItemSwichHolder(private var rootView: View) : ItemModelHolder(rootView) {
    fun bind(item: ItemSwich, listener: ActionsMenu) {
        rootView.idMenuItem.text = item.title
        rootView.idIcon.setImageResource(item.icon)
        when (item.theme) {
            R.style.AppThemeDark -> rootView.idSwich.isChecked = true
        }
        rootView.ItemMenu.setOnClickListener {
            rootView.idSwich.isChecked = !rootView.idSwich.isChecked
          //  listener.onSwichChange(rootView.idSwich.isChecked)
        }
        rootView.idSwich.setOnCheckedChangeListener { buttonView, isChecked ->  listener.onSwichChange(isChecked) }
    }
}