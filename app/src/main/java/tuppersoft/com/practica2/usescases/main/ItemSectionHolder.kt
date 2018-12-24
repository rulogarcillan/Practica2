package tuppersoft.com.practica2.usescases.main

import android.view.View
import kotlinx.android.synthetic.main.nav_section_main.view.*

class ItemSectionHolder(private var rootView: View) : ItemModelHolder(rootView) {
    fun bind(item: ItemSection) {
        rootView.idSection.text = item.section
    }
}