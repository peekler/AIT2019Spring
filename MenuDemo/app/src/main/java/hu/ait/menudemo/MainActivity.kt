package hu.ait.menudemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_help){
            Toast.makeText(this, "HELP", Toast.LENGTH_LONG).show()
        } else if (item?.itemId == R.id.action_start){
            Toast.makeText(this, "STARTING...", Toast.LENGTH_LONG).show()
        } else if (item?.itemId == R.id.action_bottom){
            startActivity(Intent(this, BottomNavActivity::class.java))
        } else if (item?.itemId == R.id.action_nav){
            startActivity(Intent(this, NavDrawerActivity::class.java))
        }


        return super.onOptionsItemSelected(item)
    }

}
