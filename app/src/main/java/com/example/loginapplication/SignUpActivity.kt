package com.example.loginapplication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnSignUp: Button
    private lateinit var tvAlreadyAccount: TextView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etSignUpEmail)
        etPassword = findViewById(R.id.etSignUpPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnSignUp = findViewById(R.id.btnSignUp)
        tvAlreadyAccount = findViewById(R.id.tvAlreadyAccount)

        auth = FirebaseAuth.getInstance()

        btnSignUp.setOnClickListener {
            if (validateInput()) {
                val email = etEmail.text.toString().trim()
                val password = etPassword.text.toString().trim()

                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            auth.currentUser?.sendEmailVerification()?.addOnCompleteListener { verifyTask ->
                                if (verifyTask.isSuccessful) {
                                    Toast.makeText(this, "Account created. Verify your email!", Toast.LENGTH_LONG).show()
                                    startActivity(Intent(this, LoginActivity::class.java))
                                    finish()
                                } else {
                                    Toast.makeText(this, "Failed to send verification email: ${verifyTask.exception?.message}", Toast.LENGTH_LONG).show()
                                }
                            }
                        } else {
                            Toast.makeText(this, "Sign Up Failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }

        tvAlreadyAccount.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun validateInput(): Boolean {
        val name = etName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val confirmPassword = etConfirmPassword.text.toString().trim()

        if (name.isEmpty()) { etName.error = "Name required"; return false }
        if (email.isEmpty()) { etEmail.error = "Email required"; return false }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) { etEmail.error = "Enter valid email"; return false }

        if (password.isEmpty()) {
            etPassword.error = "Password required"
            return false
        }

        // Password validation: min 8 chars, 1 uppercase, 1 special char
        val passwordPattern = Regex("^(?=.*[A-Z])(?=.*[@#\$%^&+=!]).{8,}$")
        if (!passwordPattern.containsMatchIn(password)) {
            etPassword.error = "Password must be 8+ chars, include 1 uppercase & 1 special char"
            return false
        }

        if (confirmPassword.isEmpty()) { etConfirmPassword.error = "Confirm your password"; return false }
        if (password != confirmPassword) { etConfirmPassword.error = "Passwords do not match"; return false }

        return true
    }
}
