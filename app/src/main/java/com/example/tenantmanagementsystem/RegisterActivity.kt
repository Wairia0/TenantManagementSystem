package com.example.tenantmanagementsystem

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantmanagementsystem.databinding.ActivityRegisterBinding
import android.util.Log
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    companion object {
        private const val TAG = "RegisterLifecycle"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "RegisterActivity: onCreate")
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.registerButton.setOnClickListener {
            val fullName = binding.fullNameEditText.text.toString().trim()
            val email = binding.registerEmailEditText.text.toString().trim()
            val password = binding.registerPasswordEditText.text.toString()

            if (fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Toast.makeText(this, "Account created. Please log in.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("EMAIL", email)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

            startActivity(intent)
        }
        binding.loginLinkTextView.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "RegisterActivity: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "RegisterActivity: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "RegisterActivity: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "RegisterActivity: onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "RegisterActivity: onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "RegisterActivity: onDestroy")
    }
}