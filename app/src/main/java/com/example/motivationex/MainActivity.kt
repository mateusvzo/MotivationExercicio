package com.example.motivationex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.example.motivationex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding

    private var mock = Mock()
    private var categoryId = MotivationConstants.FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        supportActionBar?.hide()

        setContentView(binding.root)

        handleUserName()
        activateMenuClick(binding.imageAll)
        refreshPhrase(categoryId)

        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
        binding.imageLogout.setOnClickListener(this)
    }

    override fun onClick(view: View) {

        val listButtons = listOf(
            binding.imageAll.id,
            binding.imageHappy.id,
            binding.imageSunny.id,
            binding.imageLogout.id
        )

        if (view.id == binding.buttonNewPhrase.id) {
            refreshPhrase(categoryId)
        } else if (view.id in listButtons) {
            activateMenuClick(view)
        }
    }

    private fun handleUserName() {
        var name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textName.text = "Olá, $name!"
    }

    private fun activateMenuClick(view: View) {

        binding.imageAll.setColorFilter(getColor(R.color.dark_purple))
        binding.imageHappy.setColorFilter(getColor(R.color.dark_purple))
        binding.imageSunny.setColorFilter(getColor(R.color.dark_purple))
        binding.imageLogout.setColorFilter(getColor(R.color.dark_purple))

        when (view.id) {
            binding.imageAll.id -> {
                binding.imageAll.setColorFilter(getColor(R.color.white))
                categoryId = MotivationConstants.FILTER.ALL
            }
            binding.imageHappy.id -> {
                binding.imageHappy.setColorFilter(getColor(R.color.white))
                categoryId = MotivationConstants.FILTER.HAPPY
            }
            binding.imageSunny.id -> {
                binding.imageSunny.setColorFilter(getColor(R.color.white))
                categoryId = MotivationConstants.FILTER.SUNNY
            }
            binding.imageLogout.id -> {
                binding.imageLogout.setColorFilter(getColor(R.color.white))
                clearKey()
            }
        }
    }

    private fun clearKey() {
        SecurityPreferences(this).deleteKey()
        startActivity(Intent(this, UserActivity::class.java))
        finish()
    }

    private fun refreshPhrase(value: Int) {
        var randomPhrase = mock.filterPhrase(value)
        binding.textPhrase.text = randomPhrase
    }
}