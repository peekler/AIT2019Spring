package hu.ait.highlowgame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_game.*
import java.lang.Exception
import java.util.*

class GameActivity : AppCompatActivity() {

    private var generatedNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        btnGuess.setOnClickListener {
            try {
                if (!TextUtils.isEmpty(etGuess.text)) {

                    var myNumber = Integer.parseInt(etGuess.text.toString())

                    when {
                        myNumber == generatedNum -> {
                            tvStatus.text = "You have won!"

                            // show win activity
                            startActivity(
                                Intent(this@GameActivity,
                                ResultActivity::class.java)
                            )
                        }
                        myNumber < generatedNum -> tvStatus.text = "Your number is smaller"
                        myNumber > generatedNum -> tvStatus.text = "Your number is larger"
                    }
                } else {
                    etGuess.error = "This field can not be empty"
                }
            } catch (e: Exception) {
                tvStatus.text = "Error ${e.message}"
            }
        }


        if (savedInstanceState != null){
            generatedNum = savedInstanceState.getInt("KEY_GEN")
        } else {
            generateInitialGameNumber()
        }

    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putInt("KEY_GEN", generatedNum)
    }



    private fun generateInitialGameNumber() {
        val random = Random(System.currentTimeMillis())
        generatedNum = random.nextInt(100) // 0..99
    }
}
