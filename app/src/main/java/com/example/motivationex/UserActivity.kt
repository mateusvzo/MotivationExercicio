package com.example.motivationex

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import com.example.motivationex.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)

        supportActionBar?.hide()

        setContentView(binding.root)

        userVerification()

        binding.buttonSave.setOnClickListener(this)
    }

    override fun onClick(view: View) {

        if (view.id == binding.buttonSave.id) {
            handleUser()
        }
    }

    private fun handleUser(){
        val name = binding.editName.text.toString()
        if(name != "") {
            SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, name)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, R.string.invalid_name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun userVerification() {
        val userName = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        if(userName != "") {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}