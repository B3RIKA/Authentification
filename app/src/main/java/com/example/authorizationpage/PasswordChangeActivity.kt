package com.example.authorizationpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class PasswordChangeActivity : AppCompatActivity() {
    private lateinit var newpass: EditText
    private lateinit var pass_change: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_change)
        init()

        registerListeners()
    }
    private fun init(){
        newpass = findViewById(R.id.newpass)
        pass_change = findViewById(R.id.pass_change)
    }
    private fun registerListeners() {
        pass_change.setOnClickListener() {

            val newpass = newpass.text.toString()

            if (newpass.isEmpty() || newpass.length < 9) {
                Toast.makeText(
                    this,
                    "პაროლი უნდა შეადგენდეს მინიმუმ 9 სიმბოლოს",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            } else if (!newpass.contains(0 - 9)){
                Toast.makeText(this, "პაროლი უნდა შეიცავდეს ციფრს", Toast.LENGTH_SHORT).show()
            return@setOnClickListener
        }
            FirebaseAuth.getInstance()
                .currentUser?.updatePassword(newpass)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "პაროლი წარმატებით შეიცვალა", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this,"მოხდა შეცდომა", Toast.LENGTH_SHORT).show()
                    }
                }

        }
    }
}

operator fun Any.not(): Boolean {
    return true

}

private fun String.contains(i: Int) {
    return

}
