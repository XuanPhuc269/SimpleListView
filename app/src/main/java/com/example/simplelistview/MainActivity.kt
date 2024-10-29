package com.example.simplelistview

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val inputNumber = findViewById<EditText>(R.id.input_number)

        val typeOfNumber = findViewById<RadioGroup>(R.id.type_of_number)
        val option1 = findViewById<RadioButton>(R.id.option_1)
        val option2 = findViewById<RadioButton>(R.id.option_2)
        val option3 = findViewById<RadioButton>(R.id.option_3)

        val showBtn = findViewById<Button>(R.id.show_btn)

        val adapter: ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1
        )
        val listView = findViewById<ListView>(R.id.result_list_view)
        listView.adapter = adapter

        val errorText = findViewById<TextView>(R.id.error_input_text)

        showBtn.setOnClickListener{
            val inputText = inputNumber.text.toString().toIntOrNull()
            if (inputText != null) {
                val n = inputText.toInt()
                val selectedOption = typeOfNumber.checkedRadioButtonId
                when (selectedOption) {
                    option1.id -> {
                        val result = evenNumber(n)
                        adapter.clear()
                        adapter.addAll(result.split(", "))
                    }
                    option2.id -> {
                        val result = oddNumber(n)
                        adapter.clear()
                        adapter.addAll(result.split(", "))
                    }
                    option3.id -> {
                        val result = squareNumber(n)
                        adapter.clear()
                        adapter.addAll(result.split(", "))
                    }
                }
            } else {
                errorText.text = "Invalid input text. Please enter a positive number."
                errorText.visibility = TextView.VISIBLE
            }
        }

    }

    private fun evenNumber(n: Int): String {
        val evenNumbers = (0..n).filter { it % 2 == 0 }
        return evenNumbers.joinToString(", ")
    }

    private fun oddNumber(n: Int): String {
        val oddNumbers = (1..n).filter { it % 2 != 0 }
        return oddNumbers.joinToString(", ")
    }

    private fun squareNumber(n: Int): String {
        val squareNumbers = (0..n).map { it * it }
        return squareNumbers.joinToString(", ")
    }
}