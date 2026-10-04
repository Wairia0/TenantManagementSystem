package com.example.tenantmanagementsystem

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        val tenantNameEditText =
            findViewById<EditText>(R.id.tenantNameEditText)

        val phoneEditText =
            findViewById<EditText>(R.id.phoneEditText)

        val rentEditText =
            findViewById<EditText>(R.id.rentEditText)

        val saveButton =
            findViewById<Button>(R.id.saveButton)

        val tenantResultTextView =
            findViewById<TextView>(R.id.tenantResultTextView)

        saveButton.setOnClickListener {

            val name = tenantNameEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val rent = rentEditText.text.toString()

            tenantResultTextView.text =
                "Tenant: $name\nPhone: $phone\nRent Paid: $rent"
        }
    }
}