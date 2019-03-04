package hu.ait.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import hu.ait.tictactoe.model.TicTacToeModel
import hu.ait.tictactoe.view.TicTacToeView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRestart.setOnClickListener {
            ticTacToeView.resetGame()
            //shimmer.startShimmer()
        }

    }



    public fun TicTacToeView.resetGame(){
        TicTacToeModel.resetModel()
        invalidate()
    }


    fun setStatusText(statusText: String) {
       tvStatus.text = statusText
   }
}
