package hu.ait.aithellocafateria

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCafateria.setOnClickListener {
            tvStatus.text= "Best cafeteria: Graphisoft main cafeteria"
            Toast.makeText(this, Date(System.currentTimeMillis()).toString(),
                Toast.LENGTH_LONG).show()
        }

    }
}
