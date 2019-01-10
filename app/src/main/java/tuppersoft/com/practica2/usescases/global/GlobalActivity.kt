package tuppersoft.com.practica2.usescases.global

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tuppersoft.com.data.Repository
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.dialogs.DialogActions
import tuppersoft.com.practica2.extensions.log

abstract class GlobalActivity : AppCompatActivity(), DialogActions {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(Repository.loadPreference(this, "theme", R.style.AppTheme) as Int)
        super.onCreate(savedInstanceState)
    }

    override fun buttonPositive(requestCode: Int) {
        "positive".log()
    }

    override fun buttonNegative(requestCode: Int) {
        "negative".log()
    }
}

