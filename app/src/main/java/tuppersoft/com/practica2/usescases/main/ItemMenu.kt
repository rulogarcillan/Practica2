package tuppersoft.com.practica2.usescases.main

import tuppersoft.com.practica2.usescases.global.TYPE_MENU

class ItemMenu(val title: String, val icon: Int) : ItemModel {
    override fun type(): Int = TYPE_MENU
}