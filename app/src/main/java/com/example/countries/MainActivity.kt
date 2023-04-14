package com.example.countries

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.lifecycle.lifecycleScope
import com.example.countries.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("SetTextI18n", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // AutoComplete
        val countriesArray = resources.getStringArray(R.array.countries_array)
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, countriesArray)
        binding.editTextCountryName.setAdapter(adapter)
        // Enter key
        binding.editTextCountryName.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(binding.editTextCountryName.windowToken, 0)

                binding.buttonCountrySearch.performClick()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        binding.buttonCountrySearch.setOnClickListener {
            val countryName = binding.editTextCountryName.text.toString()
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(binding.editTextCountryName.windowToken, 0)

            binding.progressBar.visibility = View.VISIBLE // Show progress bar
            binding.statusLayout.visibility = View.INVISIBLE

            lifecycleScope.launch {
                    try {
                        val countries = restCountriesApiFull.getCountryByName(countryName)
                        val country = countries[0]

                        binding.textViewCountryName.text = country.name
                        binding.textViewCapitalName.text = country.capital
                        binding.textViewPopulation.text = numberConvert(country.population)
                        binding.textViewLanguage.text = languageConvert(country.languages)
                        binding.textViewArea.text = numberConvert(country.area)
                        binding.textViewCallingCode.text = callingCodeConvert(country.callingCodes)
                        loadSvg(binding.imageView, country.flag)

                        binding.progressBar.visibility = View.GONE // Hide progress bar
                        binding.resultLayout.visibility = View.VISIBLE
                    } catch (e: UnknownHostException) {
                        binding.statusTextView.text = "Could not connect to server"
                        binding.statusImageView.setImageResource(R.drawable.baseline_error_24)
                        binding.progressBar.visibility = View.GONE
                        binding.resultLayout.visibility = View.INVISIBLE
                        binding.statusLayout.visibility = View.VISIBLE
                    } catch (e: Exception) {
                        try {
                            val countries = restCountriesApi.getCountryByName(countryName)
                            val country = countries[0]

                            binding.textViewCountryName.text = country.name
                            binding.textViewCapitalName.text = country.capital
                            binding.textViewPopulation.text = numberConvert(country.population)
                            binding.textViewLanguage.text = languageConvert(country.languages)
                            binding.textViewArea.text = numberConvert(country.area)
                            binding.textViewCallingCode.text = callingCodeConvert(country.callingCodes)
                            loadSvg(binding.imageView, country.flag)

                            binding.progressBar.visibility = View.GONE
                            binding.resultLayout.visibility = View.VISIBLE
                        } catch (e: Exception) {
                            binding.statusTextView.text = "Country not found"
                            binding.statusImageView.setImageResource(R.drawable.baseline_error_24)
                            binding.progressBar.visibility = View.GONE // Hide progress bar
                            binding.resultLayout.visibility = View.INVISIBLE
                            binding.statusLayout.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }