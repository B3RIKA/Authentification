package com.example.authorizationpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class PasswordResetActivity : AppCompatActivity() {
    private lateinit var email_reset: EditText
    private lateinit var button_reset: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_reset)
        init()
        registerListeners()
    }
    private fun init() {

        email_reset = findViewById(R.id.email_reset)
        button_reset = findViewById(R.id.button_reset)
    }
    private fun registerListeners(){
        button_reset.setOnClickListener() {
            val email = button_reset.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(this, "მეილის ველი ცარიელია", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
                else if (!email.contains("@")){
                    Toast.makeText(this,"შეიყვანეთ ვალიდური მეილი", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
                }
            FirebaseAuth.getInstance()
                .sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this,"აღდგენის ინსტრუქცია გაიგზავნა თქვენს მეილზე", Toast.LENGTH_SHORT).show()
                } else {
                        Toast.makeText(this, "მოხდა შეცდომა", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}