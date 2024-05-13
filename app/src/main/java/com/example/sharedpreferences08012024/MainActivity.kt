package com.example.sharedpreferences08012024

import android.content.SharedPreferences
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

    // Shared Preferences nơi lưu trữ data local basic
    private var sharedPreferences: SharedPreferences? = null

    // Editor dùng để thực hiện xử lý thêm, xoá, sửa của SharedPreferences
    private var editor: SharedPreferences.Editor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        edtEmail = findViewById(R.id.edit_text_email)
        edtPassword = findViewById(R.id.edit_text_password)
        btnLogin = findViewById(R.id.button_login)
        cbRemember = findViewById(R.id.checkbox_remember)

        // init Shared Preferences
        sharedPreferences = getSharedPreferences("my-shared-preferences", MODE_PRIVATE)
        editor = sharedPreferences?.edit()

        // Lấy dữ liệu từ SharedPreferences
        val email = sharedPreferences?.getString("email", "")
        val password = sharedPreferences?.getString("password", "")
        val isCheckedRemember = sharedPreferences?.getBoolean("remember", false)

        // Set dữ liệu vào EditText và CheckBox
        edtEmail?.setText(email)
        edtPassword?.setText(password)
        cbRemember?.isChecked = isCheckedRemember ?: false

        btnLogin?.setOnClickListener {
            val email = edtEmail?.text.toString()
            val password = edtPassword?.text.toString()
            val isCheckedRemember = cbRemember?.isChecked ?: false

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val emailExpected = "android0801@gmail.com"
            val passwordExpected = "11111111"

            if (email == emailExpected && password == passwordExpected) {
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                if (isCheckedRemember) {
                    editor?.putString("email", email)
                    editor?.putString("password", password)
                    editor?.putBoolean("remember", true)
                    editor?.apply()
                } else {
                    editor?.remove("email")
                    editor?.remove("password")
                    editor?.remove("remember")
                    editor?.apply()
                }
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}