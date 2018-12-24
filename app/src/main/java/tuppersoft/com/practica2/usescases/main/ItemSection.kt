package tuppersoft.com.practica2.usescases.main

import tuppersoft.com.practica2.usescases.global.TYPE_SECTION

class ItemSection(val section: String) : ItemModel {
    override fun type(): Int = TYPE_SECTION
}