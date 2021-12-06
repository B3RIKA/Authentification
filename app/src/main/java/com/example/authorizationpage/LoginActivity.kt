package com.example.authorizationpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password1: EditText
    private lateinit var login: Button
    private lateinit var register1: Button
    private lateinit var forgotpass: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
        registerListeners()
    }
    private fun init(){
        email = findViewById(R.id.email)
        password1 = findViewById(R.id.password1)
        login = findViewById(R.id.login)
        register1 = findViewById(R.id.register1)
        forgotpass = findViewById(R.id.forgotpass)
    }

    private fun registerListeners() {
        register1.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)

        }

        forgotpass.setOnClickListener {
            val intent = Intent(this, PasswordChangeActivity::class.java)
            startActivity(intent)
        }
        login.setOnClickListener {

            val email = email.text.toString()
            val password1 = password1.text.toString()

            if (email.isEmpty() || password1.isEmpty())
                Toast.makeText(this, "მეილის ან პაროლის ველი ცარიელია", Toast.LENGTH_SHORT).show()
            return@setOnClickListener
        }
        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email.toString(), password1.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    gotoProfile()
                } else {
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun gotoProfile() {
        val intent = Intent(this, ContactsContract.Profile::class.java)
        startActivity(intent)
        finish()
    }
}