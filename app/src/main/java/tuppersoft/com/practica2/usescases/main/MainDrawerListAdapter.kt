package tuppersoft.com.practica2.usescases.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.usescases.global.*

class MainDrawerListAdapter(val menu: MutableList<ItemModel>, val listener: ActionsMenu) :
    GlobalAdapter<ItemModel>(menu) {

    override fun onCreateViewHolder(rootView: ViewGroup, itemViewType: Int): RecyclerView.ViewHolder =

        when (itemViewType) {
            TYPE_MENU -> ItemMenuHolder(
                LayoutInflater.from(rootView.context).inflate(
                    R.layout.nav_item_main,
                    rootView,
                    false
                )
            )
            TYPE_SECTION -> ItemSectionHolder(
                LayoutInflater.from(rootView.context).inflate(
                    R.layout.nav_section_main,
                    rootView,
                    false
                )
            )
            TYPE_HEADER -> ItemModelHolder(
                LayoutInflater.from(rootView.context).inflate(
                    R.layout.nav_header_main,
                    rootView,
                    false
                )
            )
            TYPE_SWICH -> ItemSwichHolder(
                LayoutInflater.from(rootView.context).inflate(
                    R.layout.nav_swich_main,
                    rootView,
                    false
                )
            )
            else -> {
                ItemModelHolder(LayoutInflater.from(rootView.context).inflate(null, rootView, false))
            }
        }

    override fun onBindViewHolder(rootView: RecyclerView.ViewHolder, position: Int) {
        if (rootView is ItemMenuHolder) {
            val itemMenu = getItem(position)
            rootView.bind(itemMenu as ItemMenu, listener)
        }
        if (rootView is ItemSwichHolder) {
            val itemMenu = getItem(position)
            rootView.bind(itemMenu as ItemSwich, listener)
        }
        if (rootView is ItemSectionHolder) {
            val itemMenu = getItem(position)
            rootView.bind(itemMenu as ItemSection)
        }
    }

    override fun getItemViewType(position: Int): Int = getItem(position).type()

}
