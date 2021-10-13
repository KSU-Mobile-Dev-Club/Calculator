package edu.ksu.cs.mdc.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private var equation : String = ""
    private var result : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * Called by buttons for numbers and operators.
     *
     * Fills the equation variable and displays it.
     */
    fun buttonPress(view: android.view.View) {
        val btn : Button? = view as? Button
        val btntext : String? = btn?.text.toString()
        this.equation += btntext
        val display : TextView = findViewById(R.id.calculator_textview)
        display.setText(this.equation)
    }

    /**
     * Called by hitting the = button.
     *
     * Displays the result of the equation.
     */
    fun equalsButtonPress(view: android.view.View) {
        val pieces = convertToMath(this.equation)
        val display : TextView = findViewById(R.id.calculator_textview)
        display.setText(calculate(pieces))
    }

    /**
     * Called by the equalsButtonPress method to find the result of the equation.
     *
     * @param equationParts A List of Strings consisting of numbers and operators.
     */
    private fun calculate(equationParts : List<String>) : String {
        var answer : Double = equationParts[0].toDouble()
        var x : Int = 1
        var temp : String = equationParts[2]
        val size : Int = equationParts.size
        while (x < size) {
            when (temp) {
                "+" -> {
                    answer += equationParts[x].toDouble()
                    temp = equationParts[x]
                }
                "-" -> {
                    answer -= equationParts[x].toDouble()
                    temp = equationParts[x]
                }
                "*" -> {
                    answer *= equationParts[x].toDouble()
                    temp = equationParts[x]
                }
                "/" -> {
                    answer /= equationParts[x].toDouble()
                    temp = equationParts[x]
                }
                else -> equationParts[x].also { temp = it }
            }
            x++
        }
        return answer.toString()
    }

    /**
     * Called by pressing the C button.
     *
     * Resets the state of the calculator to starting conditions.
     */
    fun clearButtonPress(view: android.view.View) {
        this.equation = ""
        this.result = ""
        val display : TextView = findViewById(R.id.calculator_textview)
        display.setText(this.equation)
    }

    /**
     * Called by pressing the Backspace button
     *
     * Removes the last character from the equation.
     */
    fun backspaceButtonPress(view: android.view.View) {
        this.equation = this.equation.dropLast(1)
        val display : TextView = findViewById(R.id.calculator_textview)
        display.setText(this.equation)
    }

    /**
     * Converts the single String equation into a List<String> of
     * separated numbers and operators.
     */
    private fun convertToMath(equation: String): List<String> {
        var converted : List<String> = mutableListOf()
        var x = 0
        var piece : String = ""
        while (x < equation.length) {
            if (equation.elementAt(x).isDigit()) {
                piece += equation.elementAt(x)
                if (x == equation.length - 1) {
                    converted += piece
                }
            } else if (equation.elementAt(x) == '.') {
                piece += equation.elementAt(x)
                if (x == equation.length - 1) {
                    converted += piece + "0"
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