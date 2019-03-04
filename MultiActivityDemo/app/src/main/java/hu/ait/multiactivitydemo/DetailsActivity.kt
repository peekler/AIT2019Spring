package hu.ait.multiactivitydemo

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        if (intent.extras.containsKey(MainActivity.KEY_NAME) and intent.extras.containsKey(MainActivity.KEY_GRADE)){
            val name = intent.getStringExtra(MainActivity.KEY_NAME)
            val grade = intent.getStringExtra(MainActivity.KEY_GRADE)

            tvData.text = getString(R.string.text_grade,name,grade)
        }

        btnCancel.setOnClickListener {
            finish()
        }




        btnOk.setOnClickListener {
            var intentGradeResult = Intent()
            intentGradeResult.putExtra("KEY_COMMENT", etComment.text.toString())


            setResult(Activity.RESULT_OK, intentGradeResult)
            finish()
        }

    }


    override fun onBackPressed() {
        //super.onBackPressed()

        Toast.makeText(this, "You can never exit", Toast.LENGTH_LONG).show()
    }

}
