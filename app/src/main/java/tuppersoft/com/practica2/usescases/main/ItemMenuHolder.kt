package tuppersoft.com.practica2.usescases.main

import android.view.View
import kotlinx.android.synthetic.main.nav_item_main.view.*

class ItemMenuHolder(private var rootView: View) : ItemModelHolder(rootView) {
    fun bind(item: ItemMenu, listener: ActionsMenu) {
        rootView.idMenuItem.text = item.title
        rootView.idIcon.setImageResource(item.icon)
        rootView.ItemMenu.setOnClickListener { listener.onClickItemMenu(item.title) }
    }
}