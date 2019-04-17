package com.example.peter.animationdemo

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

        val anim = AnimationUtils.loadAnimation(this, R.anim.push_anim)
        val sendAnim = AnimationUtils.loadAnimation(this, R.anim.send_anim)

        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                Toast.makeText(this@MainActivity, "Animation finished", Toast.LENGTH_LONG).show()
                // start new Activity here
            }

            override fun onAnimationStart(animation: Animation?) {

            }
        })

        btnAnim.setOnClickListener {
            btnAnim.startAnimation(anim)
        }
        btnSendAnim.setOnClickListener {
            tvMessage.startAnimation(sendAnim)
        }

    }
}
