package tuppersoft.com.practica2.usescases.global

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tuppersoft.com.practica2.R

class MainActivity : GlobalActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
