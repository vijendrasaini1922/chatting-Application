package com.infosys.whatsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import com.infosys.whatsapp.databinding.ActivityLoginBinding

class login : AppCompatActivity() {

    private lateinit var login_binding: ActivityLoginBinding

    private lateinit var editEmail:EditText
    private lateinit var editPassword:EditText
    private lateinit var loginBtn:Button
    private lateinit var signupBtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        login_binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        login_binding.apply {
            editEmail = login_binding.email
            editPassword = login_binding.password
            this@login.loginBtn = login_binding.loginBtn
            this@login.signupBtn = login_binding.signupBtn
        }

        signupBtn.setOnClickListener { jmpToSignup() }

    }

    private fun jmpToSignup(){
            val intent = Intent(this, signup::class.java)
            startActivity(intent)
    }

}