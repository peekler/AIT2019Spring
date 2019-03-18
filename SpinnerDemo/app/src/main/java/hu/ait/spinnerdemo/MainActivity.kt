package hu.ait.spinnerdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val coursesAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.array_courses, android.R.layout.simple_spinner_item
        )
        coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCourse.adapter = coursesAdapter

        spinnerCourse.onItemSelectedListener = this
    }


    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(this, parent?.getItemAtPosition(position).toString(),
            Toast.LENGTH_LONG).show()
    }
}
