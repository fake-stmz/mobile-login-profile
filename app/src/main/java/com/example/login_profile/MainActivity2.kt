package com.example.login_profile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val userName = findViewById<TextView>(R.id.username)
        var userSex: String?
        var userEmail: String?
        var userPhone: String?

        if (intent.extras == null) {
            userName.text = "Армянов Армян Армянович"
            userSex = "Мужской"
            userEmail = "example@gmail.com"
            userPhone = "+7 (777) 777-77-77"
        } else {
            val newProfileInfo = intent.extras
            userName.text = newProfileInfo?.getString("name")
            userSex = newProfileInfo?.getString("sex")
            userEmail = newProfileInfo?.getString("email")
            userPhone = newProfileInfo?.getString("phone")
            findViewById<TextView>(R.id.aboutCardText).text = newProfileInfo?.getString("about")
        }

        findViewById<TextView>(R.id.userSex).text = getString(R.string.user_sex) + " " + userSex
        findViewById<TextView>(R.id.contactsCardEmail).text = getString(R.string.contacts_email) + " " + userEmail
        findViewById<TextView>(R.id.contactsCardPhone).text = getString(R.string.contacts_phone) + " " + userPhone

        val profileInfo = Bundle()
        profileInfo.putString("name", userName.text.toString())
        profileInfo.putString("sex", userSex)
        profileInfo.putString("email", userEmail)
        profileInfo.putString("phone", userPhone)
        profileInfo.putString("about", findViewById<TextView>(R.id.aboutCardText).text.toString())

        findViewById<Button>(R.id.editButton).setOnClickListener {
            val editIntent = Intent(this, MainActivity3::class.java)
            editIntent.putExtras(profileInfo)
            startActivity(editIntent)
            finish()
        }
    }
}