package hu.ait.layoutinflaterdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.note_row.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener {
            createNoteEntry()
        }
    }

    private fun createNoteEntry() {
        val myNoteView = layoutInflater.inflate(R.layout.note_row, null, false)

        myNoteView.tvNote.text = etNewNote.text.toString()

        myNoteView.btnDelete.setOnClickListener {
            layoutContent.removeView(myNoteView)
        }

        //myNoteView.ivIcon.setImageResource(R.drawable.myIcon)

        layoutContent.addView(myNoteView, 0)

        etNewNote.setText("")
    }
}
