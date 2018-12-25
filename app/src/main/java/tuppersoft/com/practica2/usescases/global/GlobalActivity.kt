package tuppersoft.com.practica2.usescases.global

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tuppersoft.com.data.Repository
import tuppersoft.com.practica2.R

abstract class GlobalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(Repository.loadPreference(this, "theme", R.style.AppTheme) as Int)
        super.onCreate(savedInstanceState)
    }
}

