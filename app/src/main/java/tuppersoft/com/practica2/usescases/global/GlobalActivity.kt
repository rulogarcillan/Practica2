package tuppersoft.com.practica2.usescases.global

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tuppersoft.com.data.Repository
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.dialogs.DialogActions

abstract class GlobalActivity : AppCompatActivity(), DialogActions {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(Repository.loadPreference(this, "theme", R.style.AppTheme) as Int)
        super.onCreate(savedInstanceState)
    }


    //only for actions globals or notifications
    //
    override fun buttonPositive(requestCode: Int) {
    }

    override fun buttonNegative(requestCode: Int) {
    }
}

