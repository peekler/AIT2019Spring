package hu.ait.highlowgame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener {
            startActivity(
                Intent(this@MainActivity,
                  GameActivity::class.java)
            )
        }

        btnHelp.setOnClickListener {
            Toast.makeText(this@MainActivity,
                "Press start to begin", Toast.LENGTH_LONG).show()
        }

        btnAbout.setOnClickListener {
            Toast.makeText(this@MainActivity,
                "Copyright: Peter", Toast.LENGTH_LONG).show()
        }
    }
}
