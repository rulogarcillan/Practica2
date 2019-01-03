package tuppersoft.com.practica2.usescases.users

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import tuppersoft.com.domain.dto.User
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.databinding.UserItemBinding
import tuppersoft.com.practica2.usescases.global.GlobalListener


class UserHolder(private val rootView: UserItemBinding) : RecyclerView.ViewHolder(rootView.root) {

    fun bind(item: User, listener: GlobalListener) {
        rootView.user = item
        rootView.action = listener
        rootView.myActions = MyActions()
    }

    inner class MyActions {
        fun openUrl(view: View, url: String) {
            var myUrl = ""
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                myUrl = "http://$url"
            }
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(myUrl))
            startActivity(view.context, browserIntent, null)
        }

        fun sendEmail(view: View, to: String) {
            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse("mailto:$to")
            try {
                startActivity(
                    view.context,
                    Intent.createChooser(emailIntent, view.context.getString(R.string.send_email_usinng)),
                    null
                )
            } catch (ex: android.content.ActivityNotFoundException) {
                Toast.makeText(view.context, view.context.getString(R.string.send_email_usinng), Toast.LENGTH_SHORT)
                    .show()
            }

        }

        fun call(view: View, phone: String) {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
            startActivity(view.context, intent, null)
        }

        fun openMap(view: View, latitude: String, longitude: String) {
            val gmmIntentUri = Uri.parse("geo:$latitude,$longitude")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            if (mapIntent.resolveActivity(view.context.packageManager) != null) {
                startActivity(view.context, mapIntent, null)
            }
        }
    }
}