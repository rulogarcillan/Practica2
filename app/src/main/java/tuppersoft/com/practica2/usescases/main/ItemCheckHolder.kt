package tuppersoft.com.practica2.usescases.main

import android.view.View
import kotlinx.android.synthetic.main.nav_check_main.view.*
import tuppersoft.com.practica2.R


class ItemCheckHolder(private var rootView: View) : ItemModelHolder(rootView) {
    fun bind(item: ItemCheck, listener: ActionsMenu) {
        rootView.idMenuItem.text = item.title
        rootView.idIcon.setImageResource(item.icon)
        when (item.theme) {
            R.style.AppThemeDark -> rootView.idCheckBox.isChecked = true
        }
        rootView.ItemMenu.setOnClickListener {
            rootView.idCheckBox.isChecked = !rootView.idCheckBox.isChecked
        }
        rootView.idCheckBox.setOnCheckedChangeListener { buttonView, isChecked -> listener.onSwichChange(isChecked) }
    }
}