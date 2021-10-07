package edu.ksu.cs.mdc.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var equation : String = ""
    private var result : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create buttons
        val btn1 = findViewById<Button>(R.id.button_1)
        val btn2 = findViewById<Button>(R.id.button_2)
        val btn3 = findViewById<Button>(R.id.button_3)
        val btn4 = findViewById<Button>(R.id.button_4)
        val btn5 = findViewById<Button>(R.id.button_5)
        val btn6 = findViewById<Button>(R.id.button_6)
        val btn7 = findViewById<Button>(R.id.button_7)
        val btn8 = findViewById<Button>(R.id.button_8)
        val btn9 = findViewById<Button>(R.id.button_9)
        val btnzero = findViewById<Button>(R.id.button_zero)
        val btnplus = findViewById<Button>(R.id.button_plus)
        val btnminus = findViewById<Button>(R.id.button_minus)
        val btnmultiply = findViewById<Button>(R.id.button_multiply)
        val btndivide = findViewById<Button>(R.id.button_divide)
    }

    fun buttonPress(view: android.view.View) {
        val btn : Button? = view as? Button
        val btntext : String? = btn?.text.toString()
        this.equation += btntext
        val display : TextView = findViewById(R.id.calculator_textview)
        display.setText(this.equation)
    }

    fun equalsButtonPress(view: android.view.View) {
        //val pieces = this.equation.split("")
        val pieces = convertToMath(this.equation)
        val display : TextView = findViewById(R.id.calculator_textview)
        display.setText(calculate(pieces))
    }

    private fun calculate(equationparts : List<String>) : String {
        var answer : Float = equationparts[0].toFloat()
        var x : Int = 1
        var temp : String = equationparts[2]
        val size : Int = equationparts.size
        while (x < size) {
            when (temp) {
                "+" -> {
                    answer += equationparts[x].toFloat()
                }
                "-" -> {
                    answer -= equationparts[x].toFloat()
                }
                "*" -> {
                    answer *= equationparts[x].toFloat()
                }
                "/" -> {
                    answer /= equationparts[x].toFloat()
                }
                else -> equationparts[x].also { temp = it }
            }
            x++
        }
        return answer.toString()
    }

    fun clearButtonPress(view: android.view.View) {
        this.equation = ""
        this.result = ""
        val display : TextView = findViewById(R.id.calculator_textview)
        display.setText(this.equation)
    }

    fun backspaceButtonPress(view: android.view.View) {
        this.equation = this.equation.dropLast(1)
        val display : TextView = findViewById(R.id.calculator_textview)
        display.setText(this.equation)
    }

    fun convertToMath(equation: String): List<String> {
        var converted : List<String> = mutableListOf()
        var x = 0
        var piece : String = ""
        while (x < equation.length) {
            if (equation.elementAt(x).isDigit()) {
                piece += equation.elementAt(x)
                if (x == equation.length - 1) {
                    converted += piece
                }
            } else {
                converted += piece
                piece = equation.elementAt(x).toString()
                converted += piece
                piece = ""
            }
            x++
        }
        return converted
    }
}