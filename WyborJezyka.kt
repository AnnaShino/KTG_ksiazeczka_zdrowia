package com.example.ktg

//wymagane importy związane z działaniem aplikacji

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

class WyborJezyka : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        
        // poniższy fragment pozwala na usunięcie nieestetycznego górnego z nazwą layautu
        super.onCreate(savedInstanceState)
        window.setFeatureInt(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        this.supportActionBar!!.hide()
        setContentView(R.layout.wyborjezyka)

        // przyciski funkcyjne 
        val button1 = findViewById<Button>(R.id.PL)
        button1.setOnClickListener {
            val i = Intent(this, BiometryczneLogowanie::class.java)
            startActivity(i)
            this.finish()
        }

        val button2 = findViewById<Button>(R.id.EN)
        button2.setOnClickListener {
            val i = Intent(this, BiometryczneLogowanieEN::class.java)
            startActivity(i)
            this.finish()
        }


    }

    //poniższy fragment odpowiada za wychodzenie z aplikacji za pomocą przycisku funkcyjnego
    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()

            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            startActivity(intent)
            finish()
            exitProcess(0)
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(
            this,
            "Kliknij przycisk POWRÓT ponownie aby wyjść z aplikacji / Click the BACK button again to exit the application",
            Toast.LENGTH_SHORT
        ).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
}
