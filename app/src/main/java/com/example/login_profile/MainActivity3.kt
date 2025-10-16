package com.example.login_profile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val initialValues: Bundle = intent.extras!!

        val nameEditInput = findViewById<EditText>(R.id.nameInput)
        val emailEditInput = findViewById<EditText>(R.id.emailInput)
        val phoneEditInput = findViewById<EditText>(R.id.phoneInput)
        val aboutEditInput = findViewById<EditText>(R.id.aboutInput)

        nameEditInput.setText(initialValues?.getString("name"))
        emailEditInput.setText(initialValues?.getString("email"))
        phoneEditInput.setText(initialValues?.getString("phone"))
        aboutEditInput.setText(initialValues?.getString("about"))

        when (initialValues?.getString("sex")) {
            "Мужской" -> findViewById<RadioButton>(R.id.sexMale).isChecked = true
            "Женский" -> findViewById<RadioButton>(R.id.sexFemale).isChecked = true
            else -> findViewById<RadioButton>(R.id.sexOther).isChecked = true
        }

        val returnIntent = Intent(this, MainActivity2::class.java)

        findViewById<Button>(R.id.saveButton).setOnClickListener {
            val newProfileInfo = Bundle()
            newProfileInfo.putString("name", nameEditInput.text.toString())
            newProfileInfo.putString("email", emailEditInput.text.toString())
            newProfileInfo.putString("phone", phoneEditInput.text.toString())
            newProfileInfo.putString("about", aboutEditInput.text.toString())
            val sexChooseRG = findViewById<RadioGroup>(R.id.sexChoose)
            newProfileInfo.putString("sex", findViewById<RadioButton>(sexChooseRG.checkedRadioButtonId).text.toString())
            returnIntent.putExtras(newProfileInfo)
            startActivity(returnIntent)
            finish()
        }
        findViewById<Button>(R.id.discardButton).setOnClickListener {
            returnIntent.putExtras(initialValues)
            startActivity(returnIntent)
            finish()
        }
    }
}