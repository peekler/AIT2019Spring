package hu.ait.sharedpreferencesdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        val KEY_LAST = "KEY_LAST"
        val KEY_SCORE = "KEY_SCORE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvLastTime.text = getLastTime()


        saveOpenTime()
    }


    fun saveOpenTime() {
        var sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        var editor = sharedPref.edit()
        editor.putString(KEY_LAST, Date(System.currentTimeMillis()).toString())
        editor.putInt(KEY_SCORE, 1221)
        editor.apply()
    }

    fun getLastTime() : String{
        var sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        var lastTime = sharedPref.getString(KEY_LAST, "It is the FIRST time you use it")

        return lastTime
    }

}
