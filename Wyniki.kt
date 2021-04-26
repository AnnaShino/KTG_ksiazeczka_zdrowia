package com.example.ktg

//wymagane importy związane z działaniem aplikacji

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.method.ScrollingMovementMethod
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File
import java.io.FileInputStream
import kotlin.system.exitProcess

class Wyniki: AppCompatActivity()  {

    //inicjalizacja paska i działanie przycisków
    lateinit var toolbar: ActionBar

    private val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.home -> {
                        val i = Intent(this, PanelGłówny::class.java)
                        startActivity(i)
                        this.finish()
                    }
                    R.id.account -> {
                        val i = Intent(this, Dane::class.java)
                        startActivity(i)
                        this.finish()
                    }
                    R.id.chart -> {
                        val i = Intent(this, ChartFragment::class.java)
                        startActivity(i)
                        this.finish()
                    }
                }
                false
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        
        // poniższy fragment pozwala na usunięcie nieestetycznego górnego z nazwą layautu
        super.onCreate(savedInstanceState)
        window.setFeatureInt(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        this.supportActionBar!!.hide()
        setContentView(R.layout.wyniki)


        // druga część związana z bottomNavigation
        toolbar = supportActionBar!!
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        //wyświetlanie odczytanego pliku wewnętrznego
        var plik_zapisu = filesDir
        val wyniki = File(plik_zapisu, "wyniki")
        wyniki.createNewFile()
        val pokaz = FileInputStream(wyniki).bufferedReader().use { it.readText() }.toString()
        val textView: TextView = findViewById(R.id.textView93)
        textView.movementMethod = ScrollingMovementMethod()
        textView.text = pokaz

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
                "Kliknij przycisk POWRÓT ponownie aby wyjść z aplikacji",
                Toast.LENGTH_SHORT
        ).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
}
