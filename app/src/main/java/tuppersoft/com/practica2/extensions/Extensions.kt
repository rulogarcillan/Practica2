package tuppersoft.com.practica2.extensions

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import tuppersoft.com.domain.dbo.DialogData
import tuppersoft.com.practica2.BuildConfig
import tuppersoft.com.practica2.dialogs.DialogAlert
import tuppersoft.com.practica2.usescases.global.TAG
import tuppersoft.com.practica2.utils.GlideApp


fun String?.log(tag: String = TAG) {
    if (BuildConfig.DEBUG) {
        Log.d(tag, this ?: "Null value")
    }
}

fun ImageView.loadFromUrl(url: String?, width: Int? = null, height: Int? = null) {
    if (width != null && height != null) {
        GlideApp.with(context).load(url).override(width, height).into(this)
    } else {
        GlideApp.with(context).load(url).into(this)
    }
}

fun View.changeVisibility(type: Int) {
    this.post { this.visibility = type }
}

//dialogData: DialogData
fun FragmentManager.showDialog() {
    val dialog =
        DialogAlert.newInstance(
            DialogData("Prueba", "Esto es una prueba de dialogo", "cancelar", "aceptar")
        )
    dialog.show(this, "")
}