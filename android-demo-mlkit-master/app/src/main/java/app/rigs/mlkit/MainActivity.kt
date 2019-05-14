package app.rigs.mlkit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import app.rigs.mlkit.ui.MainFragment

// fork from: https://github.com/riggaroo/android-demo-mlkit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commit()
        }
    }
}
