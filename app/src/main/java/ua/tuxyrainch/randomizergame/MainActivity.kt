package ua.tuxyrainch.randomizergame

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var guess = 0
    var isGuessed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        guess = ((Math.random() * 100).toInt())
    }

    fun onClick(view: View) {
        val textViewInfo = findViewById<TextView>(R.id.labLabel)
        val editTextNumberInput = findViewById<EditText>(R.id.numberInput)
        val buttonControl = findViewById<Button>(R.id.playButton)
        val textToNumber = editTextNumberInput.text.toString()
        if (!isGuessed) {
            val inputNumber = if (textToNumber == "") null else Integer.parseInt(textToNumber)
            if (inputNumber == null) {
                textViewInfo.setText("Ви не ввели число!")
            }
            else if (inputNumber > guess) {
                textViewInfo.setText("Число має бути менше.")
            }
            else if (inputNumber < guess) {
                textViewInfo.setText("Число має бути більше.")
            }
            else if (inputNumber == guess) {
                textViewInfo.setText("Ви вгадали!")
                buttonControl.setText("Грати знову")
                isGuessed = true
            }
        }
        else {
            guess = ((Math.random() * 100).toInt())
            textViewInfo.text = getString(R.string.enterNumberInfo)
            buttonControl.text = "Грати"
            isGuessed = false
        }
        editTextNumberInput.setText("")

    }
}