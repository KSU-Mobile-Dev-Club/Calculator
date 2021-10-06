package edu.ksu.cs.mdc.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @SuppressLint("SetTextI18n")
    fun buttonPress(view: android.view.View) {
        val textView = findViewById<TextView>(R.id.calculator_textview)
        val button = view as Button
        when (view.id) {
            R.id.button_backspace -> {
                textView.text = textView.text.toString().dropLast(1)
            }
            else -> {
                textView.text = textView.text.toString() + button.text.toString()
            }
        }
    }
}