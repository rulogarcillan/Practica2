package tuppersoft.com.practica2.usescases.main

import tuppersoft.com.practica2.usescases.global.TYPE_MENU

open class ItemMenu(open val title: String, open val icon: Int) : ItemModel {
    override fun type(): Int = TYPE_MENU
}