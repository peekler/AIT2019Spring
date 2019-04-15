package hu.ait.animationdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var demoAnim = AnimationUtils.loadAnimation(
            this@MainActivity, R.anim.demo_anim
        )

        demoAnim.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                Toast.makeText(this@MainActivity,
                    "ANIMATION IS ENDED", Toast.LENGTH_LONG).show()

                //startActivities(Intent(...))
            }

            override fun onAnimationStart(animation: Animation?) {
            }
        })

        btnAnim.setOnClickListener {
            btnAnim.startAnimation(demoAnim)
            //layoutMain.startAnimation(demoAnim)
        }

    }
}
