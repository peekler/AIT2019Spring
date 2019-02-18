package hu.ait.aittimeshowapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnShowTime.setOnClickListener {
            val time =
                "${etName.text}, the time is: ${Date(System.currentTimeMillis()).toString()}"

//            Toast.makeText(this,
//                time,
//                Toast.LENGTH_LONG
//            ).show()

            tvStatus.text = time

            Snackbar.make(contentLayout,
                time,
                Snackbar.LENGTH_LONG).show()
        }

    }
}
