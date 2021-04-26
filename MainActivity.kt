package com.example.ktg

//wymagane importy związane z działaniem aplikacji

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        
        // poniższy fragment pozwala na usunięcie nieestetycznego górnego z nazwą layautu
        super.onCreate(savedInstanceState)
        window.setFeatureInt(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        this.supportActionBar!!.hide()
        setContentView(R.layout.splash_screen)

        // ponizszy fragment zwiazany z automatycznym przejsciem do kolejnego layoautu po okreslonym czasie
        val handler = Handler()
        val runnable = Runnable {
            val i = Intent(this, WyborJezyka::class.java)
            startActivity(i)
        }
        handler.postDelayed(runnable, 1500)

    }
}
