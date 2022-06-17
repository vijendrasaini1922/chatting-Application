package com.infosys.whatsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.infosys.whatsapp.databinding.ActivitySignupBinding

class signup : AppCompatActivity() {

    private lateinit var signupBinding : ActivitySignupBinding

    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var editName: EditText
    private lateinit var signupBtn: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_signup)

        supportActionBar?.hide()
        signupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        mAuth = FirebaseAuth.getInstance()

        signupBinding.apply {
            editEmail = signupBinding.email
            editPassword = signupBinding.password
            this@signup.signupBtn = signupBinding.signupBtn
            editName = signupBinding.userName
        }

        signupBtn.setOnClickListener {
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()
            //val name  = editName.text.toString()
            signUp(email, password)
        }

    }

    private fun signUp(email: String, password: String ) {
        // logic of creating user
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // code for jumping to home activity
                    val intent = Intent(this@signup, MainActivity::class.java)
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@signup, "Some error occurred", Toast.LENGTH_SHORT).show()
                }
            }
    }
}