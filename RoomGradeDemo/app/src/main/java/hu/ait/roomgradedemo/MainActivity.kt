package hu.ait.roomgradedemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import hu.ait.roomgradedemo.data.AppDatabase
import hu.ait.roomgradedemo.data.Grade
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnInsert.setOnClickListener {
            saveGrade(etName.text.toString(), etGrade.text.toString())
        }

        btnQuery.setOnClickListener {
            getGrades(etGrade.text.toString())
        }
    }


    fun saveGrade(name: String, grade: String) {
        var dbThread = Thread {
            AppDatabase.getInstance(this@MainActivity).gradeDao().saveGrade(
                Grade(null, name, grade)
            )
        }
        dbThread.start()
    }

    fun getGrades(grade: String) {
        var dbThread = Thread {
            val allGrades = AppDatabase.getInstance(this@MainActivity).gradeDao().getSpecificGrades(grade)

            runOnUiThread {
                tvResult.text = ""
                allGrades.forEach {
                    tvResult.append("${it.studentId} ${it.grade}\n")
                }
            }
        }
        dbThread.start()
    }


}
