package tuppersoft.com.practica2.usescases.main

import tuppersoft.com.practica2.usescases.global.TYPE_SWICH

class ItemSwich(override val title: String,override val icon: Int, val theme: Int) : ItemMenu(title, icon) {
    override fun type(): Int = TYPE_SWICH
}

