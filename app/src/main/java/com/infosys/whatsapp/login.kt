package com.infosys.whatsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.infosys.whatsapp.databinding.ActivityLoginBinding

class login : AppCompatActivity() {

    private lateinit var login_binding: ActivityLoginBinding

    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var loginBtn: Button
    private lateinit var signupBtn: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        login_binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()

        login_binding.apply {
            editEmail = login_binding.email
            editPassword = login_binding.password
            this@login.loginBtn = login_binding.loginBtn
            this@login.signupBtn = login_binding.signupBtn
        }

        signupBtn.setOnClickListener { jmpToSignup() }

        loginBtn.setOnClickListener {
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()

            loginToPage(email, password)
        }

    }

    private fun jmpToSignup() {
        val intent = Intent(this, signup::class.java)
        startActivity(intent)
    }

    private fun loginToPage(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent = Intent(this@login, MainActivity::class.java)
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@login, "Error", Toast.LENGTH_SHORT).show()
                }
            }

    }

}