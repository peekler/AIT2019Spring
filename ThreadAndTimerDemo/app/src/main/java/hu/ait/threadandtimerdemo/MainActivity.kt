package hu.ait.threadandtimerdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var enabled = false

    inner class MyThread : Thread() {
        override fun run() {
            while(enabled){
                runOnUiThread {
                    tvData.append("#")
                }

                sleep(500)
            }
        }
    }

    inner class MyTimerTask : TimerTask() {
        override fun run() {
            runOnUiThread {
              Toast.makeText(this@MainActivity,
                  "Hello", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private lateinit var mainTimer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btnStart.setOnClickListener {
            enabled = true
            MyThread().start()

            mainTimer = Timer()

            mainTimer.schedule(MyTimerTask(), 0, 7000)
        }

        btnStop.setOnClickListener {
            enabled = false

            mainTimer.cancel()
        }
    }

    override fun onStop() {
        super.onStop()
        enabled = false

        mainTimer.cancel()
    }
}
