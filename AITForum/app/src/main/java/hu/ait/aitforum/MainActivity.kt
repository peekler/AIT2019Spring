package hu.ait.aitforum

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnRegister.setOnClickListener {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                etEmail.text.toString(), etPassword.text.toString()
            ).addOnSuccessListener {
                Toast.makeText(this@MainActivity,
                    "REGISTER OK", Toast.LENGTH_LONG).show()
            }.addOnFailureListener{
                Toast.makeText(this@MainActivity,
                    "Register failed ${it.message}", Toast.LENGTH_LONG).show()
            }
        }
    }


}
