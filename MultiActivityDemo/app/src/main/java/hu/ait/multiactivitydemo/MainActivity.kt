package hu.ait.multiactivitydemo

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        public val KEY_NAME = "KEY_NAME"
        public val KEY_GRADE = "KEY_GRADE"

        public val REQUEST_DETAILS = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSend.setOnClickListener {
            var intentDetails = Intent()
            intentDetails.setClass(this@MainActivity,
                DetailsActivity::class.java)

            intentDetails.putExtra(KEY_NAME, etName.text.toString())
            intentDetails.putExtra(KEY_GRADE, etGrade.text.toString())

            // Activity can be started also if we know
            // the package name and the class name of it
            //intentDetails.setClassName(this@MainActivity,
            //    "com.org.facebook.MainActivity")

            //startActivity(intentDetails)

            startActivityForResult(intentDetails, REQUEST_DETAILS)

            //finish()

        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_DETAILS) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this@MainActivity, "Comment ${data?.getStringExtra("KEY_COMMENT")}",
                    Toast.LENGTH_LONG).show()
            } else
            {
                Toast.makeText(this@MainActivity, "Request cancelled", Toast.LENGTH_LONG).show()
            }
        }
    }

}
