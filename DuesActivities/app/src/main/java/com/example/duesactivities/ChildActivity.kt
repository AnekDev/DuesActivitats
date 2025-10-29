package com.example.duesactivities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity

class ChildActivity : AppCompatActivity() {

    private lateinit var tvActivityId: TextView
    private lateinit var etResult: EditText
    private lateinit var btnReturnResult: Button
    private var activityId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        tvActivityId = findViewById(R.id.tvActivityId)
        etResult = findViewById(R.id.etResult)
        btnReturnResult = findViewById(R.id.btnReturnResult)

        // Obtenir l'identificador de l'activity
        activityId = intent.getIntExtra("ACTIVITY_ID", 0)
        tvActivityId.text = "Activity Filla #$activityId"

        btnReturnResult.setOnClickListener {
            returnResult()
        }

        // Manejar el botó enrere (mètode modern, sense deprecació)
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Si l'usuari prem enrere sense introduir res, no retornem cap resultat
                setResult(RESULT_CANCELED)
                finish()
            }
        })
    }

    private fun returnResult() {
        val resultText = etResult.text.toString()

        val resultIntent = Intent().apply {
            putExtra("ACTIVITY_ID", activityId)
            putExtra("RESULT_TEXT", resultText)
        }

        setResult(RESULT_OK, resultIntent)
        finish()
    }
}