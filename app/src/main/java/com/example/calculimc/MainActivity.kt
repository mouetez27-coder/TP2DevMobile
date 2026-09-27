package com.example.calculimc

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextPoids = findViewById<EditText>(R.id.editTextPoids)
        val editTextTaille = findViewById<EditText>(R.id.editTextTaille)

        val buttonCalculer = findViewById<Button>(R.id.buttonCalculer)
        val buttonEffacer = findViewById<Button>(R.id.buttonEffacer)

        val textViewImc = findViewById<TextView>(R.id.textViewImc)
        val textViewCategorie = findViewById<TextView>(R.id.textViewCategorie)

        buttonCalculer.setOnClickListener {

            val poidsTexte = editTextPoids.text.toString().trim()
            val tailleTexte = editTextTaille.text.toString().trim()

            if (poidsTexte.isEmpty() || tailleTexte.isEmpty()) {
                Toast.makeText(
                    this,
                    "Veuillez compléter les deux champs.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val poids = poidsTexte.toDoubleOrNull()
            val taille = tailleTexte.toDoubleOrNull()

            if (poids == null || taille == null) {
                Toast.makeText(
                    this,
                    "Veuillez saisir des nombres valides.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (poids <= 0 || taille <= 0) {
                Toast.makeText(
                    this,
                    "Le poids et la taille doivent être positifs.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val imc = poids / (taille * taille)

            val imcFormate = String.format(
                Locale.US,
                "%.2f",
                imc
            )

            textViewImc.text = "IMC : $imcFormate"

            if (imc < 18.5) {

                textViewCategorie.text = "Catégorie : Insuffisance pondérale"
                textViewCategorie.setTextColor(Color.rgb(255, 152, 0))

            } else if (imc < 25) {

                textViewCategorie.text = "Catégorie : Corpulence normale"
                textViewCategorie.setTextColor(Color.rgb(0, 128, 0))

            } else if (imc < 30) {

                textViewCategorie.text = "Catégorie : Surpoids"
                textViewCategorie.setTextColor(Color.rgb(255, 152, 0))

            } else if (imc < 35) {

                textViewCategorie.text = "Catégorie : Obésité modérée"
                textViewCategorie.setTextColor(Color.RED)

            } else if (imc < 40) {

                textViewCategorie.text = "Catégorie : Obésité sévère"
                textViewCategorie.setTextColor(Color.RED)

            } else {

                textViewCategorie.text = "Catégorie : Obésité morbide"
                textViewCategorie.setTextColor(Color.rgb(139, 0, 0))
            }
        }

        buttonEffacer.setOnClickListener {

            editTextPoids.text.clear()
            editTextTaille.text.clear()

            textViewImc.text = getString(R.string.imc_initial)
            textViewCategorie.text = getString(R.string.categorie_initiale)

            textViewCategorie.setTextColor(Color.BLACK)

            editTextPoids.requestFocus()
        }
    }
}