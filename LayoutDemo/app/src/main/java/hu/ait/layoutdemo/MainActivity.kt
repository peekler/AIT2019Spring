package hu.ait.layoutdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.login_layout.*

class MainActivity : AppCompatActivity() {

    private val cityNames = arrayOf("Budapest", "Bukarest", "New York", "New Delhi")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        //setContentView(R.layout.linear_demo)
        //setContentView(R.layout.constraint_demo)
        setContentView(R.layout.login_layout)

        val cityAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            cityNames
        )
        autoCompleteCity.setAdapter(cityAdapter)
    }
}
