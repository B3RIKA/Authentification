package com.example.authorizationpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var repeat_password: EditText
    private lateinit var buttonr: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()

        registerListeners()
    }

    private fun init() {

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        repeat_password = findViewById(R.id.repeat_password)
        buttonr = findViewById(R.id.buttonr)
    }

    private fun registerListeners() {
        buttonr.setOnClickListener() {

            val email = email.text.toString()
            val password = password.text.toString()
            val repeat_password = repeat_password.text.toString()

            if (email.isEmpty() || password.isEmpty() || repeat_password.isEmpty()) {
                Toast.makeText(this, "ველი ცარიელია", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if (!email.contains("@")) {
                Toast.makeText(this, "შეიყვანეთ ვალიდური მეილი", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if (!email.contains(".")) {
                Toast.makeText(this, "შეიყვანეთ ვალიდური მეილი", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if ((password.length < 9)) {
                Toast.makeText(this, "პაროლი უნდა შეადგენდეს მინიმუმ 9 სიმბოლოს", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            else if (!password.contains("1") and !password.contains("2") and !password.contains("3")
                    and !password.contains("4") and !password.contains("5") and !password.contains("6")
                    and !password.contains("7") and !password.contains("8") and !password.contains("9")
                    and !password.contains("0")){
                    Toast.makeText(this, "პაროლი უნდა შეიცავდეს ციფრს", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
            }
            else if (!repeat_password.equals(password)){
                Toast.makeText(this,"პაროლები არ ემთხვევა ერთმანეთს", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@RegisterActivity, "მოხდა შეცდომა, გადაამოწმეთ ინფორმაცია", Toast.LENGTH_SHORT).show()
                        }
                    }
            }

        }
    }
}