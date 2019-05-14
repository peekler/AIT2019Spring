package hu.ait.android.intentdemo

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnIntent.setOnClickListener {
            //intentSearch()
            //phoneCall()
            //intentSend()
            intentWaze()
        }
    }

    fun intentSearch() {
        val intentSearch = Intent(Intent.ACTION_WEB_SEARCH)
        intentSearch.putExtra(SearchManager.QUERY, "Balaton")
        startActivity(intentSearch)
    }

    private fun phoneCall() {
        val intentCall = Intent(Intent.ACTION_CALL,
            Uri.parse("tel:+3612312312"))
        startActivity(intentCall)
    }

    private fun intentSend() {
        val intentSend = Intent(Intent.ACTION_SEND)
        intentSend.type = "text/plain"
        intentSend.putExtra(Intent.EXTRA_TEXT, "Android share demo")

        intentSend.setPackage("com.facebook.katana")

        startActivity(intentSend)
        //startActivity(Intent.createChooser(intentSend,
        //        "Select share app"));
    }

    private fun intentWaze() {
        //String wazeUri = "waze://?favorite=Home&navigate=yes";
        //val wazeUri = "waze://?ll=40.761043, -73.980545&navigate=yes"
        val wazeUri = "waze://?q=AIT&navigate=yes"

        val intentTest = Intent(Intent.ACTION_VIEW)
        intentTest.data = Uri.parse(wazeUri)
        startActivity(intentTest)
    }
}
