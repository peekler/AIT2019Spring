package hu.ait.aitcalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPlus.setOnClickListener {
            if (fieldsAreFilled()) {
                val result = Integer.parseInt(etNumA.text.toString()) +
                        Integer.parseInt(etNumB.text.toString())

                tvResult.text = "The result is: $result"

                Log.d("TAG_DEMO", "plus button pressed")
            }
        }

        btnMinus.setOnClickListener {
            if (fieldsAreFilled()) {
                val result = Integer.parseInt(etNumA.text.toString()) -
                        Integer.parseInt(etNumB.text.toString())

                tvResult.text = "The result is: $result"
            }
        }

//        val item = Item(123)
//        val special = SpecialItem(212)
//
//        demo()
//
//        val car = Car("nicecar")
//        car.drive()
    }

    fun fieldsAreFilled(): Boolean {
        if (!TextUtils.isEmpty(etNumA.text)) {
            if (!TextUtils.isEmpty(etNumB.text)) {
                return true
            } else {
                etNumB.error = "This field can not be empty"
            }
        } else {
            etNumA.error = "This field can not be empty"
        }

        return false
    }


}
