package com.example.sharedpreferences08012024

import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var edtEmail: EditText? = null
    private var edtPassword: EditText? = null
    private var btnLogin: AppCompatButton? = null
    private var cbRemember: CheckBox? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        edtEmail = findViewById(R.id.edit_text_email)
        edtPassword = findViewById(R.id.edit_text_password)
        btnLogin = findViewById(R.id.button_login)
        cbRemember = findViewById(R.id.checkbox_remember)

        btnLogin?.setOnClickListener {
            val email = edtEmail?.text.toString()
            val password = edtPassword?.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val emailExpected = "android0801@gmail.com"
            val passwordExpected = "11111111"

            if (email == emailExpected && password == passwordExpected) {
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}