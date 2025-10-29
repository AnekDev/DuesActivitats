package com.example.duesactivities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvResults: TextView
    private val results = mutableListOf<String>()

    // Registre per rebre resultats de les activities filles
    private val childActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val activityId = data?.getIntExtra("ACTIVITY_ID", 0) ?: 0
            val resultText = data?.getStringExtra("RESULT_TEXT") ?: ""

            // Afegir el resultat a la llista
            val resultEntry = "Activity $activityId: $resultText"
            results.add(resultEntry)

            // Actualitzar el TextView amb tots els resultats
            updateResultsDisplay()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResults = findViewById(R.id.tvResults)

        findViewById<Button>(R.id.btnLaunchActivity1).setOnClickListener {
            launchChildActivity(1)
        }

        findViewById<Button>(R.id.btnLaunchActivity2).setOnClickListener {
            launchChildActivity(2)
        }

        findViewById<Button>(R.id.btnLaunchActivity3).setOnClickListener {
            launchChildActivity(3)
        }
    }

    private fun launchChildActivity(activityId: Int) {
        val intent = Intent(this, ChildActivity::class.java).apply {
            putExtra("ACTIVITY_ID", activityId)
        }
        childActivityLauncher.launch(intent)
    }

    private fun updateResultsDisplay() {
        if (results.isEmpty()) {
            tvResults.text = "(Cap resultat encara)"
        } else {
            tvResults.text = results.joinToString("\n")
        }
    }
}